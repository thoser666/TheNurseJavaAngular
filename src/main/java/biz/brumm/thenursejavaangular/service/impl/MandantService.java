package biz.brumm.thenursejavaangular.service.impl;

import biz.brumm.thenursejavaangular.entity.Mandant;
import biz.brumm.thenursejavaangular.service.IMandantService;
import org.springframework.data.map.repository.config.EnableMapRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

}
