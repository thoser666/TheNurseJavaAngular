package biz.brumm.thenursejavaangular;

import biz.brumm.thenursejavaangular.entity.IUserRepository;
import biz.brumm.thenursejavaangular.entity.Mandant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.StatusResultMatchersExtensionsKt.isEqualTo;

@SpringBootTest
@AutoConfigureMockMvc
class TheNurseJavaAngularApplicationTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    IUserRepository iUserRepository;

    Mandant mandant = new Mandant("alex", "a@bc.de");


    @Test
    void saveMandant()
    {
        String name = "alex";

        Mandant erg = iUserRepository.save(mandant);

        assertEquals(name, erg.getName());
    }



}
