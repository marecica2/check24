package balla.marek.kredite24.bookstore.recommender;

import balla.marek.kredite24.bookstore.book.Book;
import balla.marek.kredite24.bookstore.book.BookDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
public class BookWithRecommendationsDto extends BookDto {

    @Getter
    @Setter
    List<Book> recommendations;
}
