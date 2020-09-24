package lipamar.filmoteka.domain.movie;

import lipamar.filmoteka.domain.movie.dto.SearchResultDto;
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
    private static final String LIST_VIEW = "foundMovies";

    @GetMapping("/{title:.+}")
    public String find(@PathVariable String title, @RequestParam(required = false) String year, Model model) {
        SearchResultDto result = loadDataFromApi(title, year);
        model.addAttribute("results", result.getMovies());
        return LIST_VIEW;
    }

    private SearchResultDto loadDataFromApi(String title, String year) {
        RestTemplate restTemplate = new RestTemplate();
        OmdbApiUriBuilder uriBuilder = new OmdbApiUriBuilder();
        uriBuilder.title(title).year(year);
        return restTemplate.getForObject(uriBuilder.build(), SearchResultDto.class);
    }
}
