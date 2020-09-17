package lipamar.filmoteka.data.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String content;
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


    public Long getId() {
        return id;
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
