package biz.brumm.thenursejavaangular;

import static org.junit.jupiter.api.Assertions.*;

import biz.brumm.thenursejavaangular.entity.Mandant;
import biz.brumm.thenursejavaangular.entity.dto.MandantDTO;
import biz.brumm.thenursejavaangular.repository.IMandantRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class TheNurseJavaAngularApplicationTests {

  @Autowired private MockMvc mvc;

  @MockBean IMandantRepository iUserRepository;

  @Autowired IMandantRepository userRepository;

  Mandant mandant = new Mandant("alex", "a@bc.de");
  MandantDTO mandantDTO = new MandantDTO();

  @Test
  void saveMandantErgNull() {
    String name = "alex";

    Mandant erg = userRepository.save(mandant);

    assertNull(erg);
  }

  @Test
  void checkMandantDTONull() {
    String name = null;

    String ergDto = mandantDTO.getName();

    assertEquals(name, ergDto);
  }

  @Test
  void checkMandantDTOWithData() {
    MandantDTO dto = new MandantDTO("alex", "a@bc.de");

    String ergDto = mandantDTO.getName();

    assertNotEquals(dto.getName(), ergDto);
  }
}
