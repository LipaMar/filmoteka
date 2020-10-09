package lipamar.filmoteka.domain.user;

import lipamar.filmoteka.domain.exception.OperationForbidden;
import lipamar.filmoteka.domain.follow.Follow;
import lipamar.filmoteka.domain.follow.FollowRepository;
import lipamar.filmoteka.domain.movie.MovieInfoController;
import lipamar.filmoteka.domain.movie.MovieSearchController;
import lipamar.filmoteka.domain.movie.dto.MovieDetailsDto;
import lipamar.filmoteka.domain.movie.dto.MovieSimpleDto;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.HashSet;
import java.util.Set;

@Controller
public class UserController implements ApplicationContextAware {

    private final UserRepository users;
    private final FollowRepository follows;
    private ApplicationContext context;
    private static final String FOLLOWS_VIEW = "follows";

    @Autowired
    public UserController(UserRepository users, FollowRepository follows) {
        this.users = users;
        this.follows = follows;
    }

    @ModelAttribute(name = "movies")
    public Set<MovieDetailsDto> movies(){
        Set<Follow> followSet = follows.findAllByFollower(getLoggedUser());
        MovieInfoController controller = context.getBean(MovieInfoController.class);
        Set<MovieDetailsDto> movies = new HashSet<>();
        for(Follow follow:followSet){
            movies.add(controller.getMovie(follow.getMovieId()));
        }
        return movies;
    }
    @GetMapping("/following")
    public String userFollows(){
        return FOLLOWS_VIEW;
    }
    private User getLoggedUser() throws OperationForbidden {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        User user = users.findByUsername(currentUserName);
        if (user == null) throw new OperationForbidden();
        return user;
    }

    @Override
    @Autowired
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
}
