package biz.brumm.thenursejavaangular.mapstructs.dto;

import lombok.Data;

@Data
public class AuthResponseDTO {
  private String authenticationToken;
  private String username;
  private String isAdmin;
}
