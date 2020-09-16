package lipamar.filmoteka.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


public class MovieDetailsDto {

    @JsonProperty("Title")
    private String title;
    @JsonProperty("Director")
    private String director;
    @JsonProperty("Year")
    private String productionYear;
    @JsonProperty("Plot")
    private String plot;
    @JsonProperty("Poster")
    private String posterUrl;
    @JsonProperty("Country")
    private String country;
    @JsonProperty("imdbRating")
    private String imdbRating;
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

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {

        this.plot = plot;
    }

    public String getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(String productionYear) {
        this.productionYear = productionYear;
    }

    public String getPosterUrl() {
        return posterUrl.equals("N/A")?"https://datahabitat.mx/wp-content/themes/wp-pro-real-estate-7-child/images/no-image.png":posterUrl;
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
