package biz.brumm.thenursejavaangular.dto;

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
public class UserDto implements Dto {

  private Long userId;
  private String username;
  private String email;
  private Instant created;
  private int numOfFollowers;
  private int numOfFollowing;
  private boolean followedByCurrentUser;
  private int mutualFollowers;
  private String bio;
}
