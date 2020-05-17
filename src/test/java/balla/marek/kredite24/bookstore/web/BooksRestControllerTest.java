package balla.marek.kredite24.bookstore.web;

import balla.marek.kredite24.WebConfiguration;
import balla.marek.kredite24.bookstore.book.Book;
import balla.marek.kredite24.bookstore.book.BookRepository;
import balla.marek.kredite24.bookstore.web.BooksRestController;
import balla.marek.kredite24.security.LoggedUserFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {BooksRestController.class, WebConfiguration.class})
@WebMvcTest(controllers = BooksRestController.class)
public class BooksRestControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext wac;

    @MockBean
    BookRepository repository;

    @BeforeEach
    public void before() {
        mvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .addFilter(new LoggedUserFilter())
                .build();
    }

    @Test
    public void shouldReturnUnauthorized() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                            .get("/api/books"))
           .andExpect(status().isUnauthorized());
    }

    @Test
    public void shouldReturnBookList() throws Exception {

        Book b1 = new Book();
        b1.setId("b1");
        List<Book> books = List.of(b1);
        when(repository.findAll()).thenReturn(books);
        mvc.perform(MockMvcRequestBuilders
                            .get("/api/books")
        .session(initHttpSession()))
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
                            .get("/api/books/b1/details")
                            .session(initHttpSession()))
           .andExpect(status().isOk())
           .andExpect(MockMvcResultMatchers.jsonPath("$['id']").value("b1"));
    }

    @Test
    public void shouldReturnNotFound() throws Exception {
        Book b1 = new Book();
        b1.setId("b1");
        when(repository.findById(Mockito.any())).thenThrow(new ResourceNotFoundException());
        mvc.perform(MockMvcRequestBuilders
                            .get("/api/books/unknown/details")
                            .session(initHttpSession()))
           .andExpect(status().isNotFound());
    }

    private MockHttpSession initHttpSession() {
        MockHttpSession session = new MockHttpSession();
        String user = "user@example.com";
        session.setAttribute("user", user);
        return session;
    }
}
