package biz.brumm.thenursejavaangular.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import biz.brumm.thenursejavaangular.model.Comment;
import biz.brumm.thenursejavaangular.model.MyEntity;
import biz.brumm.thenursejavaangular.model.Post;
import java.util.List;

/**
 * @author UrosVesic
 */

public interface CommentRepository extends MyRepository, JpaRepository<Comment,Long> {
    List<Comment> findAllByPost(Post post);

    @Override
    default void deleteByParent(MyEntity parent) {
        deleteAllByPost((Post) parent);
    }

    public void deleteAllByPost(Post post);
    List<Comment> findByPost_idOrderByCreatedDateDesc(Long postId);
}
