package biz.brumm.thenursejavaangular.controller;

import biz.brumm.thenursejavaangular.entity.IUserRepository;
import biz.brumm.thenursejavaangular.entity.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    // standard constructors

    private IUserRepository userRepository;

    @GetMapping("/users")
    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
    }

    @PostMapping("/users")
    void addUser(@RequestBody User user) {
        userRepository.save(user);
    }
}
