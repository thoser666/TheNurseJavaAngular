package biz.brumm.thenursejavaangular.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author UrosVesic
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class PostRequest implements Dto {

  @NotNull(message = "Title is required")
  @NotEmpty(message = "Title is required")
  String title;

  @NotNull(message = "Topic is required")
  @NotEmpty(message = "Topic is required")
  String topicName;

  @NotNull(message = "Content is required")
  @NotEmpty(message = "Content is required")
  String content;
}
