package lipamar.filmoteka.data.entities;

import javax.persistence.*;

@Entity
@Table(name = "Reviewers")
public class Reviewer {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String displayName;
    @Column
    private String sortName;
    @Column
    private String status;
    @Column
    private String bio;
    @Column
    private String seoName;
//    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//    private Multimedia multimedia;
}
