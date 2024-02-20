/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biz.brumm.thenursejavaangular.config;

import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import biz.brumm.thenursejavaangular.dto.RegisterRequest;
import biz.brumm.thenursejavaangular.model.Message;
import biz.brumm.thenursejavaangular.model.Role;
import biz.brumm.thenursejavaangular.repository.MessageRepository;
import biz.brumm.thenursejavaangular.repository.RoleRepository;
import biz.brumm.thenursejavaangular.repository.UserRepository;
import biz.brumm.thenursejavaangular.service.AuthService;
import biz.brumm.thenursejavaangular.service.RoleService;
import biz.brumm.thenursejavaangular.service.UserService;

import java.time.Instant;
import java.util.List;

/**
 *
 * @author UrosVesic
 */
@Component
@AllArgsConstructor
public class OnStartUp {

    private RoleService roleService;
    private RoleRepository roleRepository;
    private AuthService authService;
    private UserService userService;
    private UserRepository userRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void onStartup() {
        if (!roleRepository.existsByName("USER")) {
            roleService.addRole(new Role(null, "USER", "Social network registered user"));
        }
        if (!roleRepository.existsByName("ADMIN")) {
            roleService.addRole(new Role(null, "ADMIN", "Social network administrator"));
        }
        if (!userRepository.existsByUsername("uros99")) {
            authService.signup(new RegisterRequest("uros99uki@gmail.com", "uros99", "uros99"));
            userService.enableUser("uros99");
            userService.assignRole("uros99", "ADMIN");
        }

    }

}
