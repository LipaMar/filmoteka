package lipamar.filmoteka.domain.controller;

import lipamar.filmoteka.data.entities.Review;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

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
        model.addAttribute("review", new Review());
        MovieDetailsDto movieDetails = getMovieDetailsDto(id);
        model.addAttribute("movie", movieDetails);
        return "movieDetails";
    }

    private MovieDetailsDto getMovieDetailsDto(String id) {
        OmdbApiUriBuilder builder = new OmdbApiUriBuilder();
        builder.id(id);
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(builder.build(), MovieDetailsDto.class);
    }

    @PostMapping(value = "/{id}")
    public String postReview(@PathVariable String id, Review review) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        review.setAuthor(userRepository.findByUsername(currentPrincipalName).stream().findFirst().orElse(null));
        if (review.getAuthor() == null)
            throw new OperationForbidden("No author specified");
        review.setMovieID(id);
        review.setDate(new Date());
        reviewRepository.save(review);
        return "redirect:/movie/" + id;
    }
}
