package balla.marek.kredite24.bookstore;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/task/books")
public class BooksController {

    @GetMapping
    public String showBooks() {
        return "books";
    }

    @GetMapping("/{id}/details")
    public String getBook(@PathVariable("id") String bookId) {
        System.out.println(bookId);
        return "book";
    }
}
