package balla.marek.kredite24.bookstore.book;

import balla.marek.kredite24.bookstore.book.Book;
import balla.marek.kredite24.bookstore.book.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

@ExtendWith(SpringExtension.class)
@DataJpaTest(properties = "kredite24.initialization.enable=false")
public class BookRepositoryTest {

    @Autowired
    BookRepository repository;

    @Test
    public void shouldInsertBook() {
        Book book = repository.save(new Book("b1", "name", "details", new BigDecimal("10.0"), "imageUrl"));
        Assertions.assertNotNull(book.getId());
    }
}
