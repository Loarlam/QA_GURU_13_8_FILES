import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojoForJSON.Book;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class OperationsWithJson {
    private String pathToJson = "src/test/resources/json/Book.json";

    @Test
    @DisplayName("Проверка содержимого JSON-файла")
    void deserializingJson() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Book book = objectMapper.readValue(new File(pathToJson), Book.class);
        assertThat(book.getTitle()).isEqualTo("The Cuckoo's Calling");
        assertThat(book.getAuthor()).isEqualTo("Robert Galbraith");
        assertThat(book.getGenre()).isEqualTo("classic crime novel");
        assertThat(book.getDetail().getIsbn()).isNotNull();
        assertThat(book.getPrice().get(1).getCost()).isGreaterThan(5);
    }
}
