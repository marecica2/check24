package balla.marek.kredite24.bookstore;

import balla.marek.kredite24.Kredite24Application;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {Kredite24Application.class})
@WebMvcTest(controllers = BooksController.class)
public class BookStoreControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    BookRepository repository;

    @Test
    public void shouldReturnBookList() throws Exception {
        Book b1 = new Book();
        b1.setId("b1");
        List<Book> books = List.of(b1);
        when(repository.findAll()).thenReturn(books);
        mvc.perform(MockMvcRequestBuilders
                            .get("/task/books"))
           .andExpect(status().isOk())
           .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)))
           .andExpect(MockMvcResultMatchers.jsonPath("$[0]['id']").value("b1"));
    }

    @Test
    public void shouldReturnSingleBook() throws Exception {
        Book b1 = new Book();
        b1.setId("b1");
        when(repository.findById(Mockito.any())).thenReturn(Optional.of(b1));
        mvc.perform(MockMvcRequestBuilders
                            .get("/task/books/b1/details"))
           .andExpect(status().isOk())
           .andExpect(MockMvcResultMatchers.jsonPath("$['id']").value("b1"));
    }

    @Test
    public void shouldReturnNotFound() throws Exception {
        Book b1 = new Book();
        b1.setId("b1");
        when(repository.findById(Mockito.any())).thenThrow(new ResourceNotFoundException());
        mvc.perform(MockMvcRequestBuilders
                            .get("/task/books/b1/details"))
           .andExpect(status().isNotFound());
    }
}
