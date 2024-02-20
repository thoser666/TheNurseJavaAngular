package biz.brumm.thenursejavaangular.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author UrosVesic
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopicDto implements Dto {

  private Long id;

  @NotEmpty(message = "Name is required")
  @NotNull(message = "Name is required")
  @NotBlank(message = "Name is required")
  private String name;

  private String description;
  private Integer numberOfPosts;
}
