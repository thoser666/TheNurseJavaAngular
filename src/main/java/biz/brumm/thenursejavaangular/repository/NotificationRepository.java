package biz.brumm.thenursejavaangular.repository;

import biz.brumm.thenursejavaangular.model.*;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author UrosVesic
 */
public interface NotificationRepository extends JpaRepository<Notification, Long>, MyRepository {

  @Override
  default void deleteByParent(MyEntity parent) {
    deleteAllByPost((Post) parent);
  }

  void deleteAllByPost(Post post);

  Optional<Notification> findTopByTo_usernameOrderByIdDesc(String username);

  List<Notification> findByTo_usernameOrderByIdDesc(String username);

  Optional<Notification> findByFromAndToAndPostAndNotificationType(
      User from, User to, Post post, NotificationType notificationType);

  Optional<Notification> findByFromAndToAndPost(User from, User to, Post post);
}
