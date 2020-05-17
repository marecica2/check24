package balla.marek.kredite24.bookstore.recommender;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookViewRepository extends JpaRepository<BookView, BookView.BookViewId> {
}
