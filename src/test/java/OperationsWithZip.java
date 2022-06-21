import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class OperationsWithZip {

    @Test
    void insertingFilesToZip() throws IOException {
        String[] filePathNames = new File("src/test/resources/files").list();

        try (FileOutputStream fileOutputStream = new FileOutputStream("src/test/resources/zip/3in1.zip");
             ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);) {
            for (int i = 0; i < filePathNames.length; i++) {
                File currentFile = new File("src/test/resources/files/"+filePathNames[i]);
                try (FileInputStream fileInputStream = new FileInputStream(currentFile);) {
                    ZipEntry zipEntry = new ZipEntry(currentFile.getName());
                    zipOutputStream.putNextEntry(zipEntry);
                    byte[] buffer = fileInputStream.readAllBytes();
                    zipOutputStream.write(buffer, 0, buffer.length);
                }
            }
        }
    }
}
