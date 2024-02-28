package biz.brumm.thenursejavaangular;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import biz.brumm.thenursejavaangular.controller.AuthController;
import biz.brumm.thenursejavaangular.dto.AuthResponse;
import biz.brumm.thenursejavaangular.dto.LoginRequest;
import biz.brumm.thenursejavaangular.dto.RegisterRequest;
import biz.brumm.thenursejavaangular.service.AuthService;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

@SpringBootTest
@AutoConfigureMockMvc
class TheNurseJavaAngularApplicationTests {

  private AuthController authController;
  private AuthService authServiceMock;




}
