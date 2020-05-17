package balla.marek.kredite24.bookstore.recommender;

import balla.marek.kredite24.bookstore.book.Book;
import balla.marek.kredite24.users.User;
import balla.marek.kredite24.utils.Vectors;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookRecommenderService {

    private final BookViewRepository repository;

    public BookRecommenderService(BookViewRepository repository) {
        this.repository = repository;
    }

    public List<Book> getRecommendations(Book book) {
        List<BookView> bookViews = this
                .repository
                .findAll();

        List<Book> distinctBooks = distinctBooks(bookViews);
        List<User> distinctUsers = distinctUsers(bookViews);

        Map<Book, List<User>> groupedViews = groupViewsByBook(bookViews);
        double[][] viewMatrix = getMatrix(distinctBooks, distinctUsers, groupedViews);

        int idx = distinctBooks.indexOf(book);
        List<Tuple<Double, Book>> tuples = new ArrayList<>();
        for (int i = 0; i < distinctBooks.size(); i++) {
            Double sim = Vectors.cosineSimilarity(viewMatrix[idx], viewMatrix[i]);
            tuples.add(new Tuple<>(sim, distinctBooks.get(i)));
        }

        final Comparator<Tuple<Double, Book>> comparator = Comparator.<Tuple<Double, Book>>comparingDouble(Tuple::getKey)
                .reversed();

        return tuples.stream()
                     .filter(t -> t.getKey() > 0 && t.getValue() != book)
                     .sorted(comparator)
                     .map(Tuple::getValue)
                     .collect(Collectors.toList());
    }

    private List<User> distinctUsers(List<BookView> bookViews) {
        return bookViews
                .stream()
                .map(bv -> bv.getId().getUser())
                .distinct()
                .sorted(Comparator.comparing(
                        User::getEmail)).collect(Collectors.toList());
    }

    private List<Book> distinctBooks(List<BookView> bookViews) {
        return bookViews
                .stream()
                .map(bv -> bv.getId().getBook())
                .distinct()
                .sorted(Comparator.comparing(
                        Book::getId)).collect(Collectors.toList());
    }

    private double[][] getMatrix(List<Book> books, List<User> users, Map<Book, List<User>> groupedViews) {
        double[][] viewMatrix = new double[books.size()][users.size()];
        for (int i = 0; i < books.size(); i++) {
            Book b = books.get(i);
            for (int j = 0; j < users.size(); j++) {
                User u = users.get(j);
                viewMatrix[i][j] = groupedViews.get(b).contains(u) ? 1 : 0;
            }
        }
        return viewMatrix;
    }

    private Map<Book, List<User>> groupViewsByBook(List<BookView> bookViews) {
        return bookViews
                .stream()
                .collect(
                        Collectors
                                .groupingBy(bv -> bv.getId().getBook(),
                                            Collectors.mapping(bookView -> bookView.getId().getUser(),
                                                               Collectors.toList()
                                            )
                                ));
    }

    public static class Tuple<K, V> extends AbstractMap.SimpleEntry<K, V> {

        public Tuple(K key, V value) {
            super(key, value);
        }
    }

}
