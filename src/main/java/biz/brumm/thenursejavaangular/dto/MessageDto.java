package biz.brumm.thenursejavaangular.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto implements Dto {

  String from;
  String to;

  @NotEmpty(message = "Content is required")
  @NotNull(message = "Content is required")
  @NotBlank(message = "Content is required")
  String content;

  String time;
  boolean seen;
}
