package lipamar.filmoteka.domain.controller;

import lipamar.filmoteka.domain.dto.SearchResultDto;
import lipamar.filmoteka.domain.utils.OmdbApiUriBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/find")
public class MovieSearchController {
    @GetMapping("/{title:.+}")
    public String find(@PathVariable String title, @RequestParam(required = false) String year, Model model){
        RestTemplate restTemplate = new RestTemplate();
        OmdbApiUriBuilder urlBuilder = new OmdbApiUriBuilder();
        urlBuilder.title(title).year(year);
        SearchResultDto result = restTemplate.getForObject(urlBuilder.build(),SearchResultDto.class);
        model.addAttribute("results",result.getMovies());
        return "foundMovies";
    }
}
