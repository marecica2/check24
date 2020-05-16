package balla.marek.kredite24.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public String showLogin(Model model) {
        model.addAttribute("message", "message");
        model.addAttribute("tasks", "");
        return "login";
    }

    @PostMapping
    public String login(Model model) {
        System.out.println(model);
        return "login";
    }
}
