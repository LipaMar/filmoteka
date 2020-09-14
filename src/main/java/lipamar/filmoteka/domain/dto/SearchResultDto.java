package lipamar.filmoteka.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SearchResultDto {
    @JsonProperty("Search")
    private List<MovieSimpleDto> movies = null;
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

    public String getTotalResults() {
        return totalResults;
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
