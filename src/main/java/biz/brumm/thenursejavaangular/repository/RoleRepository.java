package biz.brumm.thenursejavaangular.repository;

import java.util.Optional;

import biz.brumm.thenursejavaangular.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author UrosVesic
 */
public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(String name);

    boolean existsByName(String user);
}
