package biz.brumm.thenursejavaangular.service;

import biz.brumm.thenursejavaangular.model.Role;
import biz.brumm.thenursejavaangular.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author UrosVesic
 */
@Service
@AllArgsConstructor
public class RoleService {
  private RoleRepository roleRepository;

  public void addRole(Role role) {
    roleRepository.save(role);
  }
}
