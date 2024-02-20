package biz.brumm.thenursejavaangular.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import biz.brumm.thenursejavaangular.model.Topic;

import java.util.Optional;

/**
 * @author UrosVesic
 */
public interface TopicRepository extends JpaRepository<Topic,Long> {
    Optional<Topic> getByName(String topicName);
}
