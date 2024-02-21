package biz.brumm.thenursejavaangular.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerificationEmail {

  private String subject;
  private String recipient;
  private String content;
}
