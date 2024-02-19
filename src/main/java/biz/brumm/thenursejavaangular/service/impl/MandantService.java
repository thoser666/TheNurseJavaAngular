package biz.brumm.thenursejavaangular.service.impl;

import biz.brumm.thenursejavaangular.entity.Mandant;
import biz.brumm.thenursejavaangular.service.IMandantService;
import org.springframework.data.map.repository.config.EnableMapRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@EnableMapRepositories
public class MandantService implements IMandantService {

    private final CrudRepository<Mandant, Long> repository;

    public MandantService(CrudRepository<Mandant, Long> repository) {
        this.repository = repository;
        this.repository.saveAll(defaultItems());
    }

    private static List<Mandant> defaultItems() {
        return List.of(
                new Mandant("Burger", "aa@bb.cc"),
                new Mandant("Pizza", "bb@bb.cc"),
                new Mandant("Tea", "cc@bb.cc")
        );
    }

    public List<Mandant> findAll() {
        List<Mandant> list = new ArrayList<>();
        Iterable<Mandant> items = repository.findAll();
        items.forEach(list::add);
        return list;
    }

    public Optional<Mandant> find(Long id) {
        return repository.findById(id);
    }

    public Mandant create(Mandant item) {
        // To ensure the item ID remains unique,
        // use the current timestamp.
        Mandant copy = new Mandant(
                item.getName(),
                item.getEmail()
        );
        return repository.save(copy);
    }

    public Optional<Mandant> update( Long id, Mandant newItem) {
        // Only update an item if it can be found first.
        return repository.findById(id)
                .map(oldItem -> {
                    Mandant updated = oldItem.updateWith(newItem);
                    return repository.save(updated);
                });
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }


}
