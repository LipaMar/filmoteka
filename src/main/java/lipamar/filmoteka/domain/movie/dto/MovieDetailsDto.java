package lipamar.filmoteka.domain.movie.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


public class MovieDetailsDto extends MovieSimpleDto {
    @JsonProperty("Director")
    private String director;
    @JsonProperty("Plot")
    private String plot;
    @JsonProperty("Country")
    private String country;
    @JsonProperty("imdbRating")
    private String imdbRating;
    @JsonProperty("Runtime")
    private String runtimeMin;

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getPlot() {
        return plot.equals("N/A")?"Brak opisu":plot;
    }

    public void setPlot(String plot) {

        this.plot = plot;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getImdbRating() {
        return imdbRating.equals("N/A")?"-":imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getRuntimeMin() {
        return runtimeMin;
    }

    public void setRuntimeMin(String runtimeMin) {
        this.runtimeMin = runtimeMin;
    }
}
