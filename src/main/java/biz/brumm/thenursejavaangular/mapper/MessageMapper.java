package biz.brumm.thenursejavaangular.mapper;

import biz.brumm.thenursejavaangular.dto.MessageDto;
import biz.brumm.thenursejavaangular.exception.MyRuntimeException;
import biz.brumm.thenursejavaangular.model.Message;
import biz.brumm.thenursejavaangular.repository.UserRepository;
import java.time.Instant;
import java.time.ZoneId;
import lombok.AllArgsConstructor;

/**
 * @author UrosVesic
 */
@AllArgsConstructor
public class MessageMapper implements GenericMapper<MessageDto, Message> {

  private UserRepository userRepository;

  @Override
  public Message toEntity(MessageDto dto) {
    Message message = new Message();
    message.setContent(dto.getContent());
    message.setFrom(
        userRepository
            .findByUsername(dto.getFrom())
            .orElseThrow(() -> new MyRuntimeException("User not found")));
    message.setTo(
        userRepository
            .findByUsername(dto.getTo())
            .orElseThrow(() -> new MyRuntimeException("User not found")));
    message.setSentAt(Instant.now());
    return message;
  }

  @Override
  public MessageDto toDto(Message entity) {
    MessageDto dto = new MessageDto();
    dto.setContent(entity.getContent());
    dto.setFrom(entity.getFrom().getUsername());
    dto.setTo(entity.getTo().getUsername());
    int minute = entity.getSentAt().atZone(ZoneId.of("ECT", ZoneId.SHORT_IDS)).getMinute();
    String min;
    if (minute < 10) {
      min = "0" + minute;
    } else {
      min = minute + "";
    }
    dto.setTime(
        entity.getSentAt().atZone(ZoneId.of("ECT", ZoneId.SHORT_IDS)).getHour() + ":" + min);
    dto.setSeen(entity.getSeenAt() != null);
    return dto;
  }
}
