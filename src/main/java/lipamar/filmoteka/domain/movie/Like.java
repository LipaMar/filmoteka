package lipamar.filmoteka.domain.movie;


import lipamar.filmoteka.domain.model.BaseEntity;
import lipamar.filmoteka.domain.user.User;

import javax.persistence.*;

@Entity
@Table(name = "Likes")
public class Like extends BaseEntity {
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private User user;
    @Column
    private String movieId;

    public Like() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }
}
