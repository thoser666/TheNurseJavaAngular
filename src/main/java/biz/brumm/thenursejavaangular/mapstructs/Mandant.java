package biz.brumm.thenursejavaangular.mapstructs;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Mandant {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @NotNull(message = "name is required")
  private String name;

  @NotNull(message = "Email is required")
  private String email;

  public Mandant(String name, String email) {
    this.name = name;
    this.email = email;
  }

  public Mandant() {
    this.name = null;
    this.email = null;
  }

  public Mandant updateWith(Mandant newItem) {
    this.setName(newItem.getName());
    this.setEmail(newItem.getEmail());
    return this;
  }

  // standard constructors / setters / getters / toString
}
