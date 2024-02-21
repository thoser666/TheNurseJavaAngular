package biz.brumm.thenursejavaangular.repository;

import biz.brumm.thenursejavaangular.model.Following;
import biz.brumm.thenursejavaangular.model.idclasses.FollowingId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author UrosVesic
 */
public interface FollowRepository extends JpaRepository<Following, FollowingId> {

  List<Following> findAllByFollowed_userId(Long id);
}
