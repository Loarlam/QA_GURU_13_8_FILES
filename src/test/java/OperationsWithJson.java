import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

public class OperationsWithJson {
    private String pathToJson = "src/test/resources/json/Book.json";

    @Test
    @DisplayName("Проверка содержимого JSON-файла")
    void deserializingJson() throws IOException {
        try (InputStream inputStream = OperationsWithJson.class.getClassLoader().getResourceAsStream(pathToJson)) {
            
        }
    }
}
