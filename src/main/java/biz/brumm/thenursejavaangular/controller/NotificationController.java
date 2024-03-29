package biz.brumm.thenursejavaangular.controller;

import biz.brumm.thenursejavaangular.dto.NotificationDto;
import biz.brumm.thenursejavaangular.service.NotificationService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author UrosVesic
 */
@RestController
@RequestMapping("/api/notification")
@AllArgsConstructor
public class NotificationController {

  private NotificationService notificationService;

  @GetMapping("/last/{username}")
  public ResponseEntity<NotificationDto> getLastNotificationForUser(@PathVariable String username) {
    NotificationDto dto = notificationService.getLastNotificationForUser(username);
    return new ResponseEntity<>(dto, HttpStatus.OK);
  }

  @GetMapping("/all")
  public ResponseEntity<List<NotificationDto>> getAllNotificationsForUser() {
    List<NotificationDto> list = notificationService.getAllNotificationsForUser();
    return new ResponseEntity<>(list, HttpStatus.OK);
  }

  @PatchMapping("/mark-as-read/{id}")
  public ResponseEntity markAsRead(@PathVariable Long id) {
    notificationService.markAsRead(id);
    return new ResponseEntity(HttpStatus.OK);
  }
}
