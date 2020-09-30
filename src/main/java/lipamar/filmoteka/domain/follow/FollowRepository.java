package lipamar.filmoteka.domain.follow;

import lipamar.filmoteka.domain.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface FollowRepository extends CrudRepository<Follow, Long>{
    Follow findByFollowerAndMovieId(User user, String movieId);
    Set<Follow> findAllByFollower(User user);
}
