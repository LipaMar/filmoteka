package lipamar.filmoteka.domain.controller;

import lipamar.filmoteka.data.entities.User;
import lipamar.filmoteka.domain.repository.UserRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegisterController {
    private final UserRepository repository;
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(RegisterController.class);

    @Autowired
    public RegisterController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String getRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping
    public String register(@Valid User user, Errors errors, HttpServletRequest request) {
        if(errors.hasErrors()){
            return "register";
        }
        repository.save(user);
        try {
            request.login(user.getUsername(), user.getPassword());
        } catch (ServletException e) {
            log.error("Auto login failed");
        }
        return "redirect:/";
    }
}
