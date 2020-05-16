package balla.marek.kredite24.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/task/login")
public class LoginController {

    @GetMapping
    public String showLogin(Model model) {
        model.addAttribute("loginForm", new LoginForm("test2@example.com", "secret"));
        return "login";
    }

    @PostMapping
    public String login(
            @Valid LoginForm loginForm,
            BindingResult bindingResult,
            HttpSession httpSession
    ) {
        if (bindingResult.hasErrors()) {
            return "login";
        }
        httpSession.setAttribute("user", loginForm.getEmail());
        return "redirect:/task/books";
    }
}
