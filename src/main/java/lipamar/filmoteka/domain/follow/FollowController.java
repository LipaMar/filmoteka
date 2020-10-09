package lipamar.filmoteka.domain.follow;

import lipamar.filmoteka.domain.user.User;
import lipamar.filmoteka.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/movie/{movieId}/follow")
public class FollowController {

    private final FollowRepository follows;
    private final UserRepository users;

    @Autowired
    public FollowController(FollowRepository follows, UserRepository users) {
        this.follows = follows;
        this.users = users;
    }

    @GetMapping
    @ResponseBody
    public boolean isFollowing(@PathVariable String movieId) {
        if (!isUserLoggedIn()) return false;
        User user = getLoggedUser();
        Follow follow = follows.findByFollowerAndMovieId(user, movieId);
        return follow != null;
    }

    @PostMapping
    @ResponseBody
    public boolean followTheMovie(@PathVariable String movieId) {
        User user = getLoggedUser();
        if (user == null) return false;
        Follow follow = follows.findByFollowerAndMovieId(user, movieId);
        if (follow == null) {
            follow = new Follow();
            follow.setMovieId(movieId);
            follow.setFollower(user);
            user.addFollow(follow);
            users.save(user);
            return true;
        } else {
            follows.delete(follow);
            return false;
        }
    }

    private User getLoggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        return users.findByUsername(currentUserName);
    }

    private boolean isUserLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).noneMatch(s -> s.equals("ROLE_ANONYMOUS"));
    }
}
