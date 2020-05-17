package balla.marek.kredite24.bookstore.book;

import balla.marek.kredite24.bookstore.recommender.BookView;
import balla.marek.kredite24.users.User;
import balla.marek.kredite24.utils.CSVReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

@Component
@Transactional
public class BookInitializer {

    @PersistenceContext
    EntityManager em;

    public void init() throws IOException {
        List<Book> books = initializeBooks();
        books.forEach(em::persist);
        List<BookView> bookViews = initializeBooksViews();
        bookViews.forEach(em::persist);
    }

    public List<Book> initializeBooks() throws IOException {
        InputStream csvStream = new ClassPathResource("books.csv").getInputStream();
        CSVReader<Book> csvReader = new CSVReader<>();
        return csvReader.readCSV(csvStream, row -> {
            Book b = new Book();
            b.setId(row.get("id"));
            b.setName(row.get("name"));
            b.setDetails(row.get("details"));
            b.setImage(row.get("image"));
            b.setPrice(new BigDecimal(row.get("price").replace(',', '.')));
            return b;
        });
    }

    public List<BookView> initializeBooksViews() throws IOException {
        InputStream csvStream = new ClassPathResource("books-views.csv").getInputStream();
        CSVReader<BookView> csvReader = new CSVReader<>();
        return csvReader.readCSV(csvStream, row -> {
            Book book = em.getReference(Book.class, row.get("book"));
            User user = em.getReference(User.class, row.get("user"));
            return new BookView(new BookView.BookViewId(book, user));
        });
    }
}
