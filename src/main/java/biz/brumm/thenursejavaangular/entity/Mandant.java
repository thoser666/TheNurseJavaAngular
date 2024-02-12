package biz.brumm.thenursejavaangular.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Mandant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String email;

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