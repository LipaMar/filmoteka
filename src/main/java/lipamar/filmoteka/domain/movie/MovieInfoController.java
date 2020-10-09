package lipamar.filmoteka.domain.movie;

import lipamar.filmoteka.domain.exception.OperationForbidden;
import lipamar.filmoteka.domain.movie.dto.MovieDetailsDto;
import lipamar.filmoteka.domain.review.Review;
import lipamar.filmoteka.domain.review.ReviewRepository;
import lipamar.filmoteka.domain.user.User;
import lipamar.filmoteka.domain.user.UserRepository;
import lipamar.filmoteka.domain.utils.OmdbApiUriBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Date;
import java.util.Set;

@Controller
@RequestMapping("/movie/{movieId}")
public class MovieInfoController {
    private final ReviewRepository reviews;
    private final UserRepository users;
    private final static String MOVIE_DETAILS_VIEW = "movieDetails";

    @Autowired
    public MovieInfoController(ReviewRepository reviews, UserRepository users) {
        this.reviews = reviews;
        this.users = users;
    }

    @ModelAttribute("movie")
    public MovieDetailsDto getMovie(@PathVariable String movieId) {
        return getMovieDetailsFromApi(movieId);
    }

    private MovieDetailsDto getMovieDetailsFromApi(String id) {
        OmdbApiUriBuilder builder = new OmdbApiUriBuilder();
        builder.id(id);
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(builder.build(), MovieDetailsDto.class);
    }

    @ModelAttribute("reviews")
    public Set<Review> getReviews(@PathVariable String movieId) {
        return reviews.findAllByMovieID(movieId);
    }

    @ModelAttribute("review")
    public Review getReview(@PathVariable String movieId) {
        Review review = null;
        try {
            review = reviews.findByAuthorAndMovieID(getLoggedUser(), movieId);
        } catch (OperationForbidden ignored) {
        }
        return review == null ? new Review() : review;
    }

    @ModelAttribute("avgRating")
    public double getAvgRating(@PathVariable String movieId) {
        return calculateRating(getReviews(movieId));
    }

    private double calculateRating(Set<Review> reviewSet) {
        return reviewSet.stream().mapToInt(Review::getRate).average().orElse(0);
    }

    @InitBinder
    public void setDisallowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping
    public String movieDetailsPage() {
        return MOVIE_DETAILS_VIEW;
    }

    @PostMapping
    public String postReview(@PathVariable String movieId, @Valid Review review, Errors errors) {
        if (!errors.hasErrors()) {
            saveReview(movieId, review);
        }
        return "redirect:/movie/" + movieId;
    }

    private void saveReview(String id, Review review) {
        review.setAuthor(getLoggedUser());
        review.setMovieID(id);
        review.setDate(new Date());
        reviews.save(review);
    }

    private User getLoggedUser() throws OperationForbidden {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        User user = users.findByUsername(currentUserName);
        if (user == null) throw new OperationForbidden();
        return user;
    }

}
