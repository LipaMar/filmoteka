package lipamar.filmoteka.domain.utils;

import java.net.URI;
import java.net.URISyntaxException;

public class OmdbApiUriBuilder {
    private final String apiKey = "";
    private final String base = "http://www.omdbapi.com/?apikey=" + apiKey;
    private String title = "";
    private String id = "";
    private String year = "";
    private boolean details = false;
    private OmdbType type = OmdbType.MOVIE;

    public enum OmdbType {
        MOVIE,
        SERIES,
        EPISODE;

        String getOption() {
            return this.name().toLowerCase();
        }

    }

    public URI build() {
        StringBuilder stringBuilder = new StringBuilder(base);
        stringBuilder.append(id.isBlank()?"":"&i="+id);
        stringBuilder.append(details?"&t="+title:"&s="+title);
        stringBuilder.append(year.isBlank()?"":"&y="+year);
        stringBuilder.append("&type="+type.getOption());
        URI result = null;
        try {
            result = new URI(stringBuilder.toString().replace(" ","%20"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void id(String id) {
        this.id = id;
    }

    public OmdbApiUriBuilder title(String title) {
        if(title!=null)
            this.title = title;
        return this;
    }

    public OmdbApiUriBuilder year(String year) {
        if(year!=null)
            this.year = year;
        return this;
    }

    public OmdbApiUriBuilder type(OmdbType type) {
        if(type!=null)
            this.type = type;
        return this;
    }

    public OmdbApiUriBuilder details(boolean showDetails) {
        details = showDetails;
        return this;
    }
}
