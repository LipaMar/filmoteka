package lipamar.filmoteka.domain.user;

import lipamar.filmoteka.domain.follow.Follow;
import lipamar.filmoteka.domain.model.BaseEntity;
import lipamar.filmoteka.domain.review.Review;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Users")
public class User extends BaseEntity {
    @Column
    @Pattern(regexp = "[a-zA-z0-9]{4,15}",message = "Username must be 4 to 15 characters long and must only contain letters and numbers")
    private String username;
    @Column
    @Size(min = 5,max = 15,message = "Password must be 5 to 15 characters long")
    private String password;
    @ManyToMany(mappedBy = "userLiked")
    private final Set<Review> likedReviews = new HashSet<>();
    @OneToMany(mappedBy = "follower", cascade = CascadeType.ALL, orphanRemoval = true)
    private final Set<Follow> followedMovies = new HashSet<>();

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Review> getLikedReviews() {
        return likedReviews;
    }

    public Set<Follow> getFollowedMovies() {
        return followedMovies;
    }
    public void likeReview(Review review){
        likedReviews.add(review);
    }
    public void addFollow(Follow follow){
        followedMovies.add(follow);
    }
}
