package biz.brumm.thenursejavaangular.repository;

import biz.brumm.thenursejavaangular.model.Following;
import biz.brumm.thenursejavaangular.model.idclasses.FollowingId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author UrosVesic
 */
public interface FollowRepository extends JpaRepository<Following, FollowingId> {

    List<Following> findAllByFollowed_userId(Long id);
}
