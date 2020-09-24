package lipamar.filmoteka.domain.user;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegisterController {
    private final UserRepository repository;
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(RegisterController.class);
    private String callingPage = "/";
    private static final String REGISTER_FORM = "register";

    @Autowired
    public RegisterController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String getRegisterForm(Model model, @RequestHeader(required = false) String referer) {
        model.addAttribute("user", new User());
        callingPage = referer == null ? callingPage : referer;
        return REGISTER_FORM;
    }

    @PostMapping
    public String register(@Valid User user, Errors errors, HttpServletRequest request) {
        if (errors.hasErrors()) {
            return REGISTER_FORM;
        }
        repository.save(user);
        tryToAutoLogin(user, request);
        log.info("Referer:" + callingPage);
        return "redirect:" + callingPage;
    }

    private void tryToAutoLogin(User user, HttpServletRequest request) {
        try {
            request.login(user.getUsername(), user.getPassword());
        } catch (ServletException e) {
            log.error("Auto login failed");
        }
    }
}
