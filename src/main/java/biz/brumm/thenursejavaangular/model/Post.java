package biz.brumm.thenursejavaangular.model;

import biz.brumm.thenursejavaangular.repository.MyRepository;
import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.ApplicationContext;

/**
 * @author UrosVesic
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Post implements MyEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "Post Name cannot be empty or Null")
  private String title;

  @Column(columnDefinition = "text")
  private String content;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "userId", referencedColumnName = "userId")
  private User user;

  private Instant createdDate;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "topicId", referencedColumnName = "id")
  private Topic topic;

  private Instant deletebByAdmin;

  @Override
  public List<MyRepository> returnChildRepositories(ApplicationContext context) {
    MyRepository commentRepository = (MyRepository) context.getBean("commentRepository");
    MyRepository notificationRepository = (MyRepository) context.getBean("notificationRepository");
    MyRepository reactionRepository = (MyRepository) context.getBean("reactionRepository");
    MyRepository postReportRepository = (MyRepository) context.getBean("postReportRepository");
    List<MyRepository> list = new ArrayList<>();
    list.add(commentRepository);
    list.add(notificationRepository);
    list.add(reactionRepository);
    list.add(postReportRepository);
    return list;
  }
}
