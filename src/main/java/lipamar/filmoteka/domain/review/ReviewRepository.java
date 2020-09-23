package lipamar.filmoteka.domain.review;

import lipamar.filmoteka.domain.review.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Long> {
    Set<Review> findAllByMovieID(String id);
}
