package lipamar.filmoteka.domain.review;

import lipamar.filmoteka.domain.model.BaseEntity;
import lipamar.filmoteka.domain.user.User;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Reviews")
public class Review extends BaseEntity {
    @Column
    private String content;
    @Column
    @Min(1)
    @Max(10)
    @NotNull(message = "You must rate the film")
    private Integer rate;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User author;
    @Column
    private String movieID;
    @Column
    private Date date;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "User_Review",
            joinColumns = {@JoinColumn(name = "Reviews.id")},
            inverseJoinColumns = {@JoinColumn(name = "Users.id")}
    )
    private Set<User> userLiked = new HashSet<>();

    public Review() {
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Set<User> getUserLiked() {
        return userLiked;
    }

    public void setUserLiked(Set<User> userLiked) {
        this.userLiked = userLiked;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getMovieID() {
        return movieID;
    }

    public void setMovieID(String movieID) {
        this.movieID = movieID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
