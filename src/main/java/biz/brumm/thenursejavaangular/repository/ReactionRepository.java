package biz.brumm.thenursejavaangular.repository;

import biz.brumm.thenursejavaangular.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author UrosVesic
 */

public interface ReactionRepository extends JpaRepository<Reaction,Long>,MyRepository {

    @Override
    default void deleteByParent(MyEntity parent) {
        deleteAllByPost((Post) parent);
    }

    public void deleteAllByPost(Post post);

    List<Reaction> findByPostAndReactionType(Post post, ReactionType like);

    Optional<Reaction> findByPost_idAndUser(Long postId, User currentUser);
}
