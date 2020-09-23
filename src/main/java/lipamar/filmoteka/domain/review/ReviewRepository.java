package lipamar.filmoteka.domain.review;

import lipamar.filmoteka.domain.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Long> {
    Set<Review> findAllByMovieID(String id);
    Review findByAuthorAndMovieID(User author, String movieId);
}
