import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.Assertions.assertThat;

public class OperationsWithZip {
    private String pathToFiles = "src/test/resources/files/",
            pathToZip = "src/test/resources/zip/",
            pathToPrepackageFile = "src/test/resources/zip/forUnzip.zip";

    @Test
    @DisplayName("Создание zip-файла с загрузкой оного в resource")
    void insertingFilesToZip() throws IOException, InterruptedException {
        if (Files.exists(Paths.get(pathToZip + "3in1.zip"))) {
            try {
                Files.delete(Paths.get(pathToZip + "3in1.zip"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

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
    void checkingTheZipContents() throws IOException, CsvException {
        try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(pathToPrepackageFile))) {
            ZipEntry entry;
            while ((entry = zipInputStream.getNextEntry()) != null) {
                switch (entry.getName().substring(entry.getName().lastIndexOf('.'))) {
                    case ".pdf":
                        PDF pdf = new PDF(zipInputStream);
                        assertThat(pdf.text).contains("Rebum dolor invidunt sed dolor sea wisi");
                        break;
                    case ".xlsx":
                        XLS xls = new XLS(zipInputStream);
                        assertThat(xls.excel.getSheetAt(0)
                                .getRow(1)
                                .getCell(1)
                                .getStringCellValue()).contains("Люк Бессон");
                        break;
                    case ".csv":
                        CSVReader csvFileReader = new CSVReader(new InputStreamReader(zipInputStream, UTF_8));
                        List<String[]> csvList = csvFileReader.readAll();
                        assertThat(csvList).contains(new String[]{"2021", "Что такое наука и как она работает", "Джеймс Цимринг"});
                        break;
                }
            }
        }
    }
}