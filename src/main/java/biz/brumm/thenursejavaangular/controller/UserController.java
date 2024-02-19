package biz.brumm.thenursejavaangular.controller;

import biz.brumm.thenursejavaangular.entity.Mandant;
import biz.brumm.thenursejavaangular.service.IMandantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @PostMapping
    public ResponseEntity<Mandant> create(@RequestBody Mandant item) {
        Mandant created = service.create(item);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mandant> update(
            @PathVariable("id") Long id,
            @RequestBody Mandant updatedItem) {

        Optional<Mandant> updated = service.update(id, updatedItem);

        return updated
                .map(value -> ResponseEntity.ok().body(value))
                .orElseGet(() -> {
                    Mandant created = service.create(updatedItem);
                    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(created.getId())
                            .toUri();
                    return ResponseEntity.created(location).body(created);
                });
    }

    // ✨ New! 👇 DELETE definition ✨
    @DeleteMapping("/{id}")
    public ResponseEntity<Mandant> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
