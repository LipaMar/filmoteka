package lipamar.filmoteka.data.entities;

import javax.persistence.*;

@Entity
@Table(name = "Reviews")
public class Review {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String content;
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private User author;

    public Review() {
    }
}
