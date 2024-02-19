package biz.brumm.thenursejavaangular.service.impl;

import biz.brumm.thenursejavaangular.entity.Mandant;
import biz.brumm.thenursejavaangular.entity.dto.MandantDTO;
import biz.brumm.thenursejavaangular.service.IMandantService;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.map.repository.config.EnableMapRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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

}
