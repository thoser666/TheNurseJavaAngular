package biz.brumm.thenursejavaangular.repository;

import biz.brumm.thenursejavaangular.model.Post;
import biz.brumm.thenursejavaangular.model.Topic;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author UrosVesic
 */
public interface PostRepository extends JpaRepository<Post, Long> {
  List<Post> findByTopicAndDeletebByAdminIsNull(Topic topic);

  List<Post> findAllByUser_usernameAndDeletebByAdminIsNull(String username);

  List<Post> findByUser_userIdInAndDeletebByAdminIsNull(List<Long> following);

  List<Post> findByTopic_name(String topicName);
}
