package biz.brumm.thenursejavaangular.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Mandant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private final String name;
    private final String email;

    public Mandant(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Mandant() {
        this.name = null;
        this.email = null;
    }

    // standard constructors / setters / getters / toString
}