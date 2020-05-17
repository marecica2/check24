package balla.marek.kredite24.bookstore.web;

import balla.marek.kredite24.bookstore.book.Book;
import balla.marek.kredite24.bookstore.book.BookDto;
import balla.marek.kredite24.bookstore.book.BookRepository;
import balla.marek.kredite24.bookstore.recommender.BookRecommenderService;
import balla.marek.kredite24.bookstore.recommender.BookWithRecommendationsDto;
import org.modelmapper.ModelMapper;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/books")
public class BooksRestController {

    private final BookRepository bookRepository;

    private final BookRecommenderService recommenderService;

    private final ModelMapper mapper;

    public BooksRestController(BookRepository bookRepository, BookRecommenderService recommenderService, ModelMapper mapper) {
        this.bookRepository = bookRepository;
        this.recommenderService = recommenderService;
        this.mapper = mapper;
    }

    @GetMapping
    List<BookDto> getBooks() {
        return this.bookRepository
                .findAll()
                .stream()
                .map(book -> this.mapper.map(book, BookDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}/details")
    BookDto getBookDetail(@PathVariable("id") String id) {
        Book book = this.bookRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        List<Book> recommendations = this.recommenderService.getRecommendations(book);
        BookWithRecommendationsDto dto = this.mapper.map(book, BookWithRecommendationsDto.class);
        dto.setRecommendations(recommendations);
        return dto;
    }
}
