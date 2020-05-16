package balla.marek.kredite24.bookstore;

import balla.marek.kredite24.Kredite24Application;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;

@ActiveProfiles("test")
@SpringBootTest
@ContextConfiguration(classes = {Kredite24Application.class})
public class BookRepositoryTest {

    @Autowired
    BookRepository repository;

    @Test
    public void shouldInsertBook() {
        Book book = repository.save(new Book("b1", "name", "details", new BigDecimal("10.0"), "imageUrl"));
        Assertions.assertNotNull(book.getId());
    }
}
