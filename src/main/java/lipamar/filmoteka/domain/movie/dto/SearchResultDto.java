package lipamar.filmoteka.domain.movie.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class SearchResultDto {
    @JsonProperty("Search")
    private List<MovieSimpleDto> movies = new ArrayList<>();
    @JsonProperty("totalResults")
    private String totalResults;
    @JsonProperty("Response")
    private String response;

    public List<MovieSimpleDto> getMovies() {
        return movies;
    }

    public void setMovies(List<MovieSimpleDto> movies) {
        this.movies = movies;
    }

    public int getTotalResults() {
        try {
            return Integer.parseInt(totalResults);
        } catch (NumberFormatException e){
            return 0;
        }
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
