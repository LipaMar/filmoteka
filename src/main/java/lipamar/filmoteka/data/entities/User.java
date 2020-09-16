package lipamar.filmoteka.data.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String username;
    @Column
    private String password;
    @ManyToMany(mappedBy = "userLiked")
    private Set<Review> likedReviews = new HashSet<>();

    public User() {
    }

    public Long getId() {
        return id;
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
}
