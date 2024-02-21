package biz.brumm.thenursejavaangular.service;

import biz.brumm.thenursejavaangular.dto.NotificationDto;
import biz.brumm.thenursejavaangular.exception.MyRuntimeException;
import biz.brumm.thenursejavaangular.mapper.NotificationMapper;
import biz.brumm.thenursejavaangular.model.Notification;
import biz.brumm.thenursejavaangular.repository.NotificationRepository;
import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author UrosVesic
 */
@AllArgsConstructor
@Service
public class NotificationService {

  private WebSocketService webSocketService;
  private NotificationRepository notificationRepository;
  private NotificationMapper notificationMapper;
  private AuthService authService;

  @Transactional
  public void save(Notification notification) {
    if (!notification
        .getTo()
        .getUsername()
        .equals(SecurityContextHolder.getContext().getAuthentication().getName())) {
      Notification notification1 =
          notificationRepository
              .findByFromAndToAndPost(
                  notification.getFrom(), notification.getTo(), notification.getPost())
              .orElse(notification);
      if (notification.equals(notification1)) {
        notificationRepository.save(notification);
        webSocketService.sendNotificationToUser(notification.getTo().getUsername(), "notification");
        return;
      }

      if (!notification1.isRead()
          && notification.getNotificationType().equals(notification1.getNotificationType())) {
        notification1.setCreated_at(Instant.now());
        notificationRepository.save(notification1);
      } else {
        notificationRepository.delete(notification1);
        notificationRepository.save(notification);
        webSocketService.sendNotificationToUser(notification.getTo().getUsername(), "notification");
      }
    }
  }

  public NotificationDto getLastNotificationForUser(String username) {
    Notification notification =
        notificationRepository
            .findTopByTo_usernameOrderByIdDesc(username)
            .orElseThrow(() -> new MyRuntimeException("Notification not found"));
    return notificationMapper.toDto(notification);
  }

  public List<NotificationDto> getAllNotificationsForUser() {
    List<Notification> notifications =
        notificationRepository.findByTo_usernameOrderByIdDesc(
            authService.getCurrentUser().getUsername());
    return notifications.stream()
        .sorted(Comparator.comparing(Notification::getCreated_at).reversed())
        .map(n -> notificationMapper.toDto(n))
        .collect(Collectors.toList());
  }

  public void markAsRead(Long id) {
    Notification notification =
        notificationRepository
            .findById(id)
            .orElseThrow(() -> new MyRuntimeException("Notification not found"));
    notification.setRead(true);
    notificationRepository.save(notification);
  }
}
