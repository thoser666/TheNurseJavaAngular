package biz.brumm.thenursejavaangular.model.idclasses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Embeddable;
import jakarta.persistence.IdClass;
import java.io.Serializable;

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
