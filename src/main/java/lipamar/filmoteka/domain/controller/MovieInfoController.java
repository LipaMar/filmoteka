package lipamar.filmoteka.domain.controller;

import lipamar.filmoteka.data.entities.Review;
import lipamar.filmoteka.data.entities.User;
import lipamar.filmoteka.domain.dto.MovieDetailsDto;
import lipamar.filmoteka.domain.exception.OperationForbidden;
import lipamar.filmoteka.domain.repository.ReviewRepository;
import lipamar.filmoteka.domain.repository.UserRepository;
import lipamar.filmoteka.domain.utils.OmdbApiUriBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Date;
import java.util.Set;

@Controller
@RequestMapping("/movie")
public class MovieInfoController {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(MovieInfoController.class);

    public MovieInfoController(ReviewRepository reviewRepository, UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
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
