package balla.marek.kredite24.bookstore.recommender;

import balla.marek.kredite24.bookstore.book.Book;
import balla.marek.kredite24.users.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest(properties = "kredite24.initialization.enable=false")
@Transactional
public class BookRecommenderServiceTest {

    @Autowired
    private EntityManager em;

    @Autowired
    private BookRecommenderService service;

    @Test
    public void shouldReturnRecommendations() {
        List<Book> books = List.of(
                new Book("b1", "name1", "details1", new BigDecimal(10), "img"),
                new Book("b2", "name2", "details2", new BigDecimal(15), "img"),
                new Book("b3", "name3", "details3", new BigDecimal(20), "img")
        );
        books.forEach(em::persist);

        List<User> users = List.of(
                new User("u1"),
                new User("u2"),
                new User("u3")
        );
        users.forEach(em::persist);

        List.of(
                new BookView(new BookView.BookViewId(books.get(0), users.get(0))),
                new BookView(new BookView.BookViewId(books.get(1), users.get(0))),
                new BookView(new BookView.BookViewId(books.get(2), users.get(0))),
                new BookView(new BookView.BookViewId(books.get(0), users.get(1))),
                new BookView(new BookView.BookViewId(books.get(1), users.get(1))),
                new BookView(new BookView.BookViewId(books.get(2), users.get(1))),
                new BookView(new BookView.BookViewId(books.get(2), users.get(2)))
        ).forEach(em::persist);
        em.flush();

        List<Book> bookRecommendations = service.getRecommendations(books.get(0));
        Assertions.assertEquals(0, bookRecommendations.size());
    }
}
