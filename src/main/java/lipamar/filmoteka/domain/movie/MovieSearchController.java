package lipamar.filmoteka.domain.movie;

import lipamar.filmoteka.domain.movie.dto.MovieSimpleDto;
import lipamar.filmoteka.domain.movie.dto.SearchResultDto;
import lipamar.filmoteka.domain.utils.OmdbApiUriBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/find")
public class MovieSearchController {
    private static final String LIST_VIEW = "foundMovies";
    private static final int MOVIES_COUNT_IN_RESPONSE = 10;
    private static final int API_REQUEST_LIMIT = 5;
    private int totalResults = 0;

    @GetMapping("/{title:.+}")
    public String find(@PathVariable String title, @RequestParam(required = false) String year, @RequestParam(required = false) Integer page, Model model) {
        model.addAttribute("results", loadMovies(title, year, page));
        return LIST_VIEW;
    }

    private List<MovieSimpleDto> loadMovies(String title, String year, Integer page) {
        if (page == null) page = 1;
        List<MovieSimpleDto> result = new ArrayList<>();
        totalResults = loadDataFromApi(title, year, 1).getTotalResults();
        for (int requestNr = 1; requestNr <= totalResults / MOVIES_COUNT_IN_RESPONSE && requestNr < API_REQUEST_LIMIT; requestNr++) {
            var data = loadDataFromApi(title, year, requestNr + ((page - 1) * MOVIES_COUNT_IN_RESPONSE)).getMovies();
            result.addAll(data);
        }
        return result;
    }

    private SearchResultDto loadDataFromApi(String title, String year, int page) {
        RestTemplate restTemplate = new RestTemplate();
        OmdbApiUriBuilder uriBuilder = new OmdbApiUriBuilder();
        uriBuilder.title(title).year(year).page(page);
        return restTemplate.getForObject(uriBuilder.build(), SearchResultDto.class);
    }
}
