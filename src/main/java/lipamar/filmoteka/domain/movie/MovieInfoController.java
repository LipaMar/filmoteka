package lipamar.filmoteka.domain.movie;

import lipamar.filmoteka.domain.review.Review;
import lipamar.filmoteka.domain.user.User;
import lipamar.filmoteka.domain.movie.dto.MovieDetailsDto;
import lipamar.filmoteka.domain.exception.OperationForbidden;
import lipamar.filmoteka.domain.review.ReviewRepository;
import lipamar.filmoteka.domain.user.UserRepository;
import lipamar.filmoteka.domain.utils.OmdbApiUriBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Date;
import java.util.Set;

@Controller
@RequestMapping("/movie")
public class MovieInfoController {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    public MovieInfoController(ReviewRepository reviewRepository, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
    }
    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping(value = "/{id}")
    public String movie(@PathVariable String id, Model model) {
        MovieDetailsDto movieDetails = getMovieDetailsDto(id);
        Set<Review> reviewSet = reviewRepository.findAllByMovieID(id);
        model.addAttribute("movie", movieDetails);
        model.addAttribute("review", new Review());
        model.addAttribute("reviews", reviewSet);
        model.addAttribute("avg", getAvgRating(id, reviewSet));
        return "movieDetails";
    }

    private double getAvgRating(String id, Set<Review> reviewSet) {
        return reviewSet.stream().mapToInt(Review::getRate).average().orElse(0);
    }

    private MovieDetailsDto getMovieDetailsDto(String id) {
        OmdbApiUriBuilder builder = new OmdbApiUriBuilder();
        builder.id(id);
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(builder.build(), MovieDetailsDto.class);
    }

    @PostMapping(value = "/{id}")
    public String postReview(@PathVariable String id, @Valid Review review, Errors errors) {
        if (!errors.hasErrors()) {
            saveReview(id, review);
        }
        return "redirect:/movie/" + id;
    }

    private void saveReview(String id, Review review) {
        review.setAuthor(getLoggedUser());
        review.setMovieID(id);
        review.setDate(new Date());
        reviewRepository.save(review);
    }

    private User getLoggedUser() throws OperationForbidden {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return userRepository.findByUsername(currentPrincipalName).stream().findFirst().orElseThrow(() -> new OperationForbidden("Anonymous users cannot add reviews"));
    }

}
