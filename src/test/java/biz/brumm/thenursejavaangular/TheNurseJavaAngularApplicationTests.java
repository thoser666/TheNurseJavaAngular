package biz.brumm.thenursejavaangular;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
class TheNurseJavaAngularApplicationTests {

	@Test
	void contextLoads() {
		assertEquals(1,1);
	}

}
