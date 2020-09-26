package lipamar.filmoteka.domain.follow;


import lipamar.filmoteka.domain.model.BaseEntity;
import lipamar.filmoteka.domain.user.User;

import javax.persistence.*;

@Entity
@Table(name = "Follows")
public class Follow extends BaseEntity {
    @ManyToOne
    private User follower;
    @Column
    private String movieId;

    public Follow() {
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public User getFollower() {
        return follower;
    }

    public void setFollower(User follower) {
        this.follower = follower;
    }
}
