package biz.brumm.thenursejavaangular.repository;

import biz.brumm.thenursejavaangular.model.Comment;
import biz.brumm.thenursejavaangular.model.MyEntity;
import biz.brumm.thenursejavaangular.model.Post;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author UrosVesic
 */
public interface CommentRepository extends MyRepository, JpaRepository<Comment, Long> {
  List<Comment> findAllByPost(Post post);

  @Override
  default void deleteByParent(MyEntity parent) {
    deleteAllByPost((Post) parent);
  }

  void deleteAllByPost(Post post);

  List<Comment> findByPost_idOrderByCreatedDateDesc(Long postId);
}
