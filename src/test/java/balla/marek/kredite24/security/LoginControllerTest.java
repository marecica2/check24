package balla.marek.kredite24.security;

import balla.marek.kredite24.security.LoginController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {LoginController.class})
@WebMvcTest(controllers = LoginController.class, excludeAutoConfiguration = JpaRepositoriesAutoConfiguration.class)
public class LoginControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void shouldLoginAndRedirect() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                            .post("/task/login")
                            .param("email", "valid@valid.com")
                            .param("password", "secret")
                            .contentType(MediaType.MULTIPART_FORM_DATA))
           .andExpect(status().is3xxRedirection())
           .andExpect(MockMvcResultMatchers.header().string("location", "/task/books"));
    }

    @Test
    public void shouldNotLogInWhenSubmittingInvalidEmail() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                            .post("/task/login")
                            .param("email", "invalid@test.com")
                            .param("password", "secret")
                            .contentType(MediaType.MULTIPART_FORM_DATA))
           .andExpect(status().isOk()).andExpect(MockMvcResultMatchers.content()
                                                                      .string(containsString("Invalid Email")));
    }
}
