package lipamar.filmoteka.data.entities;

import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @Pattern(regexp = "[a-zA-z0-9]{4,15}",message = "Username must be 4 to 15 characters long and must only contain letters and numbers")
    private String username;
    @Column
    @Size(min = 5,max = 15,message = "Password must be 5 to 15 characters long")
    private String password;
    @ManyToMany(mappedBy = "userLiked")
    private final Set<Review> likedReviews = new HashSet<>();

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
