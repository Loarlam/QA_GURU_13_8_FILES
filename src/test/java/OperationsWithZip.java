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
        String[] files = {
                "src/test/resources/files/1.pdf",
                "src/test/resources/files/2.xlsx",
                "src/test/resources/files/3.csv"
        };

        try (FileOutputStream fileOutputStream = new FileOutputStream("D:\\1.zip");
             ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);) {
            for (int i = 0; i < files.length; i++) {
                File currentFile = new File(files[i]);
                FileInputStream fileInputStream = new FileInputStream(currentFile);
                ZipEntry zipEntry = new ZipEntry(currentFile.getName());
                zipOutputStream.putNextEntry(zipEntry);
                byte[] buffer = fileInputStream.readAllBytes();
                zipOutputStream.write(buffer, 0, buffer.length);
            }
        }
    }
}
