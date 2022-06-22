import org.junit.jupiter.api.*;

import java.io.*;
import java.util.zip.*;

import static org.assertj.core.api.Assertions.assertThat;

public class OperationsWithZip {
    private String pathToFiles = "src/test/resources/files/",
            pathToZip = "src/test/resources/zip/";

    @Test
    @DisplayName("Создание zip-файла с загрузкой оного в resource")
    void insertingFilesToZip() throws IOException {
        String[] filePathNames = new File(pathToFiles).list();

        try (FileOutputStream fileOutputStream = new FileOutputStream(pathToZip + "3in1.zip");
             ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);) {
            for (int i = 0; i < filePathNames.length; i++) {
                File currentFile = new File(pathToFiles + filePathNames[i]);
                try (FileInputStream fileInputStream = new FileInputStream(currentFile);) {
                    ZipEntry zipEntry = new ZipEntry(currentFile.getName());
                    zipOutputStream.putNextEntry(zipEntry);
                    byte[] buffer = fileInputStream.readAllBytes();
                    zipOutputStream.write(buffer, 0, buffer.length);
                }
            }
        }

        try (InputStream inputArchive = new FileInputStream(pathToZip + "3in1.zip");
             ZipInputStream zipInputStream = new ZipInputStream(inputArchive)) {
            ZipEntry zipEntry = zipInputStream.getNextEntry();
            assertThat(zipEntry.getName().length()).isGreaterThan(0);
        }
    }

    @Test
    @DisplayName("Проверка содержимого zip-архива")
    void checkingTheZipContents ()
    {

    }
}