package lipamar.filmoteka.domain.movie;

import lipamar.filmoteka.domain.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends CrudRepository<Like, Long>{
    Like findByUserAndMovieId(User user, String movieId);
}
