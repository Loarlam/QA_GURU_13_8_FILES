import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojoForJSON.Book;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class OperationsWithJson {
    private String pathToJson = "src/test/resources/json/Book.json";

    @Test
    @DisplayName("Проверка содержимого JSON-файла")
    void deserializingJson() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Book book = objectMapper.readValue(new File(pathToJson), Book.class);
        System.out.println(book);
    }
}
