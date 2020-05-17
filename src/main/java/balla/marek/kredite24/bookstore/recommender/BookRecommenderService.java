package balla.marek.kredite24.bookstore.recommender;

import balla.marek.kredite24.bookstore.book.Book;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookRecommenderService {

    private BookViewRepository repository;

    public BookRecommenderService(BookViewRepository repository) {
        this.repository = repository;
    }

    public List<Book> getRecommendations(Book book) {
        Comparator<BookView> userComparator = Comparator
                .comparing(bookView -> bookView.getId().getUser().getEmail());

        Map<Book, Set<BookView>> groups = getBookViewMap(userComparator);


        return Collections.emptyList();
    }

    private Map<Book, Set<BookView>> getBookViewMap(Comparator<BookView> userComparator) {
        return this
                .repository
                .findAll()
                .stream()
                .collect(
                        Collectors
                                .groupingBy(bv -> bv.getId().getBook(),
                                            Collectors.toCollection(() -> new TreeSet<>(userComparator))
                                ));
    }
}
