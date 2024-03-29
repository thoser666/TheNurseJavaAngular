package biz.brumm.thenursejavaangular.mapper;

import biz.brumm.thenursejavaangular.model.*;
import biz.brumm.thenursejavaangular.service.AuthService;
import java.time.Instant;
import lombok.AllArgsConstructor;

/**
 * @author UrosVesic
 */
@AllArgsConstructor
public class NotificationBuilder {

  private AuthService authService;

  public Notification createNotificationForReaction(Reaction react) {
    Notification not = new Notification();
    not.setFrom(react.getUser());
    not.setTo(react.getPost().getUser());
    not.setCreated_at(Instant.now());
    not.setPost(react.getPost());
    not.setRead(false);
    not.setNotificationType(setNotificationType(react.getReactionType()));
    return not;
  }

  public Notification createNotificationForComment(Comment comment) {
    Notification not = new Notification();
    not.setFrom(comment.getUser());
    not.setTo(comment.getPost().getUser());
    not.setCreated_at(Instant.now());
    not.setPost(comment.getPost());
    not.setRead(false);
    not.setNotificationType(NotificationType.COMMENT);
    return not;
  }

  private NotificationType setNotificationType(ReactionType reactionType) {
    if (reactionType == ReactionType.LIKE) {
      return NotificationType.LIKE;
    } else {
      return NotificationType.DISLIKE;
    }
  }
}
