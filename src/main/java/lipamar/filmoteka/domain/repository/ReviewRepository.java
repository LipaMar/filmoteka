package lipamar.filmoteka.domain.repository;

import lipamar.filmoteka.data.entities.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Long> {
    Set<Review> findAllByMovieID(String id);
}
