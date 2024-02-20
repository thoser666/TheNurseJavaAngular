package biz.brumm.thenursejavaangular.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author UrosVesic
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {

  private String authenticationToken;
  private String username;
  private String isAdmin;
}
