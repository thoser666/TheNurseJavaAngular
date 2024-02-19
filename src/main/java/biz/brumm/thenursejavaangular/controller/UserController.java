package biz.brumm.thenursejavaangular.controller;

import biz.brumm.thenursejavaangular.entity.Mandant;
import biz.brumm.thenursejavaangular.repository.IMandantRepository;
import biz.brumm.thenursejavaangular.service.IMandantService;
import biz.brumm.thenursejavaangular.service.impl.MandantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
      private final IMandantService service;

    public UserController(IMandantService service) {
        this.service = service;
    }
    @GetMapping
    public ResponseEntity<List<Mandant>> findAll() {
        List<Mandant> items = service.findAll();
        return ResponseEntity.ok().body(items);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mandant> find(@PathVariable("id") Long id) {
        Optional<Mandant> item = service.find(id);
        return ResponseEntity.of(item);
    }
}
