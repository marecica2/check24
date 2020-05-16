package balla.marek.kredite24.utils;

import balla.marek.kredite24.bookstore.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class CSVReaderTest {

    @Test
    public void shouldReadCSV() throws IOException {
        InputStream csv = new ClassPathResource("books.csv").getInputStream();

        CSVReader<Book> reader = new CSVReader<Book>();
        List<Book> books =  reader.readCSV(csv, row -> {
            Book b = new Book();
            b.setId(row.get("id"));
            b.setName(row.get("name"));
            b.setDetails(row.get("details"));
            b.setImage(row.get("image"));
            b.setPrice(new BigDecimal(row.get("price").replace(',', '.')));
            return b;
        });
        Assertions.assertEquals(3, books.size());
        Assertions.assertEquals("b1", books.get(0).getId());
    }
}
