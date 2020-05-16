package balla.marek.kredite24.bookstore;

import org.modelmapper.ModelMapper;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("task/books")
public class BooksController {

    private final BookRepository bookRepository;

    private final ModelMapper mapper;

    public BooksController(BookRepository bookRepository, ModelMapper mapper) {
        this.bookRepository = bookRepository;
        this.mapper = mapper;
    }

    @GetMapping
    List<BookDto> getBooks(HttpSession session) {
        System.out.println(session.getAttribute("user"));

        return this.bookRepository
                .findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}/details")
    BookDto getBookDetail(@PathVariable("id") String id) {
        Book b = this.bookRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        return convertToDto(b);
    }

    private BookDto convertToDto(Book book) {
        return this.mapper.map(book, BookDto.class);
    }
}
