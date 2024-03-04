package biz.brumm.thenursejavaangular.mapstructs.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class MandantDTO {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  private final String name;
  private final String email;

  public MandantDTO(String name, String email) {
    this.name = name;
    this.email = email;
  }

  public MandantDTO() {
    this.name = null;
    this.email = null;
  }
}
