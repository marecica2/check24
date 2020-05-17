package balla.marek.kredite24.bookstore.book;

import balla.marek.kredite24.bookstore.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, String> {
}
