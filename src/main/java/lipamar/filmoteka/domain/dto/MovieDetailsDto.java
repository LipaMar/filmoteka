package lipamar.filmoteka.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


public class MovieDetailsDto {

    @JsonProperty("Title")
    private String title;
    @JsonProperty("Director")
    private String director;
    @JsonProperty("Year")
    private String productionYear;
    @JsonProperty("Poster")
    private String posterUrl;
    @JsonProperty("Country")
    private String country;
    @JsonProperty("imdbRating")
    private Float imdbRating;
    @JsonProperty("Runtime")
    private String runtimeMin;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(String productionYear) {
        this.productionYear = productionYear;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Float getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(Float imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getRuntimeMin() {
        return runtimeMin;
    }

    public void setRuntimeMin(String runtimeMin) {
        this.runtimeMin = runtimeMin;
    }
}
