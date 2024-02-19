package biz.brumm.thenursejavaangular.controller;

import biz.brumm.thenursejavaangular.entity.Mandant;
import biz.brumm.thenursejavaangular.service.IMandantService;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
  public ResponseEntity<Mandant> create(@Valid @RequestBody Mandant item) {
    Mandant created = service.create(item);
    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(created.getId())
            .toUri();
    return ResponseEntity.created(location).body(created);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Mandant> update(
      @PathVariable("id") Long id, @Valid @RequestBody Mandant updatedItem) {

    Optional<Mandant> updated = service.update(id, updatedItem);

    return updated
        .map(value -> ResponseEntity.ok().body(value))
        .orElseGet(
            () -> {
              Mandant created = service.create(updatedItem);
              URI location =
                  ServletUriComponentsBuilder.fromCurrentRequest()
                      .path("/{id}")
                      .buildAndExpand(created.getId())
                      .toUri();
              return ResponseEntity.created(location).body(created);
            });
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Mandant> delete(@PathVariable("id") Long id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, String>> handleValidationExceptions(
      MethodArgumentNotValidException ex) {
    List<ObjectError> errors = ex.getBindingResult().getAllErrors();
    Map<String, String> map = new HashMap<>(errors.size());
    errors.forEach(
        (error) -> {
          String key = ((FieldError) error).getField();
          String val = error.getDefaultMessage();
          map.put(key, val);
        });
    return ResponseEntity.badRequest().body(map);
  }
}
