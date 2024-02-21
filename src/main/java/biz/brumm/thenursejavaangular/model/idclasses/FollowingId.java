package biz.brumm.thenursejavaangular.model.idclasses;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author UrosVesic
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FollowingId implements Serializable {

  private Long following;
  private Long followed;
}
