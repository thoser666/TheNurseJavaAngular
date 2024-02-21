package biz.brumm.thenursejavaangular.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author UrosVesic
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class InboxMessageDto implements Dto {
  String with;
  String content;
  String time;
  int newMessages;
}
