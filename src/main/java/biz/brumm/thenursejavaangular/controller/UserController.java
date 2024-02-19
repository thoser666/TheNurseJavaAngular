package biz.brumm.thenursejavaangular.controller;

import biz.brumm.thenursejavaangular.entity.Mandant;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    // standard constructors

    private IUserRepository userRepository;

    @GetMapping("/users")
    public List<Mandant> getUsers() {
        return (List<Mandant>) userRepository.findAll();
    }

    @PostMapping("/users")
    void addUser(@RequestBody Mandant mandant) {
        userRepository.save(mandant);
    }
}
