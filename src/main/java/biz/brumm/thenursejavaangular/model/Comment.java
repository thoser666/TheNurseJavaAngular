package biz.brumm.thenursejavaangular.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author UrosVesic
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comment implements MyEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotEmpty private String text;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "postId", referencedColumnName = "id")
  private Post post;

  private Instant createdDate;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "userId", referencedColumnName = "userId")
  private User user;
}
