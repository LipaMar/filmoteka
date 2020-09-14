package lipamar.filmoteka.domain.controller;

import lipamar.filmoteka.domain.dto.MovieDetailsDto;
import lipamar.filmoteka.domain.utils.OmdbApiUriBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/movie")
public class MovieInfoController {
    @GetMapping
    public String movie(@RequestParam String id, Model model) {
        OmdbApiUriBuilder builder = new OmdbApiUriBuilder();
        builder.id(id);
        RestTemplate restTemplate = new RestTemplate();
        MovieDetailsDto movieDetails = restTemplate.getForObject(builder.build(), MovieDetailsDto.class);
        model.addAttribute("movie", movieDetails);
        return "movieDetails";
    }
}
