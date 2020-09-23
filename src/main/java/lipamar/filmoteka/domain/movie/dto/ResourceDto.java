package lipamar.filmoteka.domain.movie.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResourceDto {
    
    @JsonProperty("type")
    private String type;
    @JsonProperty("src")
    private String src;
    @JsonProperty("height")
    private Integer height;
    @JsonProperty("width")
    private Integer width;
    @JsonProperty("credit")
    private String credit;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    @Override
    public String toString() {
        return "Multimedia{" +
                ", type='" + type + '\'' +
                ", src='" + src + '\'' +
                ", height=" + height +
                ", width=" + width +
                ", credit='" + credit + '\'' +
                '}';
    }
}
