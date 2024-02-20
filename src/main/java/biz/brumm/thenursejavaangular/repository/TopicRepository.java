package biz.brumm.thenursejavaangular.repository;

import biz.brumm.thenursejavaangular.model.Topic;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author UrosVesic
 */
public interface TopicRepository extends JpaRepository<Topic, Long> {
  Optional<Topic> getByName(String topicName);
}
