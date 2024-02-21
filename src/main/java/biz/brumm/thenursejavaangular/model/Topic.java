package biz.brumm.thenursejavaangular.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.List;
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
public class Topic implements MyEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "Topic name is required")
  @Column(unique = true)
  private String name;

  private String description;

  @OneToMany(fetch = FetchType.LAZY)
  private List<Post> posts;

  private Instant createdDate;

  @ManyToOne(fetch = FetchType.LAZY)
  private User user;
}
