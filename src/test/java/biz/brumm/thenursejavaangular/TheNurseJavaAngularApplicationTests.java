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

  @BeforeEach
  void setUp() {
    authServiceMock = mock(AuthService.class);
    authController = new AuthController(authServiceMock);
  }

  @Test
  void activateAccount_ValidToken_Success() {
    // Arrange
    String token = "valid_token";

    // Act
    ResponseEntity<String> responseEntity = authController.activateAccount(token);

    // Assert
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(
        "Acount succesfuly activated, you can close this page now", responseEntity.getBody());
    verify(authServiceMock).activateAccount(token);
  }

  @Test
  void signup_ValidRegisterRequest_Success() {
    // Arrange
    RegisterRequest registerRequest = new RegisterRequest();

    // Act
    ResponseEntity<String> responseEntity = authController.signup(registerRequest);

    // Assert
    assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    assertEquals("Registration succesfull", responseEntity.getBody());
    verify(authServiceMock).signup(registerRequest);
  }

  @Test
  void login_ValidLoginRequest_Success() {
    // Arrange
    LoginRequest loginRequest = new LoginRequest();
    AuthResponse expectedResponse = new AuthResponse("token", "token", "false");
    when(authServiceMock.login(loginRequest)).thenReturn(expectedResponse);

    // Act
    AuthResponse response = authController.login(loginRequest);

    // Assert
    assertEquals(expectedResponse, response);
    verify(authServiceMock).login(loginRequest);
  }

  @Test
  void handleMethodArgumentNotValidException_ExceptionThrown_BadRequest() {
    // Arrange
    MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);
    when(ex.getAllErrors()).thenReturn(List.of(new ObjectError("objectName", "error message")));

    // Act
    ResponseEntity<List<String>> responseEntity =
        authController.handleMethodArgumentNotValidException(ex);

    // Assert
    assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    assertEquals(List.of("error message"), responseEntity.getBody());
  }
}
