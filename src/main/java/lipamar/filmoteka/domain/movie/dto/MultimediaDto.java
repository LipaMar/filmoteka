package lipamar.filmoteka.domain.movie.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MultimediaDto {
    @JsonProperty("resource")
    private ResourceDto resourceDto;

    public ResourceDto getResourceDto() {
        return resourceDto;
    }

    public void setResourceDto(ResourceDto resourceDto) {
        this.resourceDto = resourceDto;
    }

    @Override
    public String toString() {
        return "Multimedia{" +
                "resource=" + resourceDto +
                '}';
    }
}
