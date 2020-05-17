package balla.marek.kredite24.bookstore.recommender;

import balla.marek.kredite24.bookstore.book.Book;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BookRecommenderService {

    private BookViewRepository repository;

    public BookRecommenderService(BookViewRepository repository) {
        this.repository = repository;
    }

    public List<Book> getRecommendations(Book book) {
        Map<Book, List<BookView>> groups = this.repository.findAll()
                                                          .stream()
                                                          .collect(Collectors.groupingBy(b -> b.getId().getBook()));
        return Collections.emptyList();
    }
}
