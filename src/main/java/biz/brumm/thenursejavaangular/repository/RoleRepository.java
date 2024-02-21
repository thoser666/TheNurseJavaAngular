package biz.brumm.thenursejavaangular.repository;

import biz.brumm.thenursejavaangular.model.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author UrosVesic
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(String name);

  boolean existsByName(String user);
}
