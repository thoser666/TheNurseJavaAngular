package biz.brumm.thenursejavaangular.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import biz.brumm.thenursejavaangular.dto.AuthResponse;
import biz.brumm.thenursejavaangular.dto.LoginRequest;
import biz.brumm.thenursejavaangular.dto.RegisterRequest;
import biz.brumm.thenursejavaangular.service.AuthService;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

  @Mock private AuthService authService;

  @InjectMocks private AuthController authController;

  @BeforeEach
  void setUp() {
    // Setup any mock behaviors if needed
  }

  @Test
  void testActivateAccount_Success() {
    String token = "someToken";
    doNothing().when(authService).activateAccount(token);

    ResponseEntity<String> response = authController.activateAccount(token);

    assert response.getStatusCode() == HttpStatus.OK;
    assert Objects.requireNonNull(response.getBody()).equals("Acount succesfuly activated, you can close this page now");
  }

  @Test
  void testSignup_Success() {
    RegisterRequest registerRequest = new RegisterRequest(/* Provide necessary details */ );
    doNothing().when(authService).signup(registerRequest);

    ResponseEntity<String> response = authController.signup(registerRequest);

    assert response.getStatusCode() == HttpStatus.CREATED;
    assert Objects.requireNonNull(response.getBody()).equals("Registration succesfull");
  }

  @Test
  void testLogin_Success() {
    LoginRequest loginRequest = new LoginRequest(/* Provide necessary details */ );
    AuthResponse expectedResponse = new AuthResponse(/* Provide expected response */ );
    when(authService.login(loginRequest)).thenReturn(expectedResponse);

    AuthResponse response = authController.login(loginRequest);

    assert response.equals(expectedResponse);
  }

  @Test
  void testDataIntegrityViolationExceptionHandling() {
    ResponseEntity<String> response = authController.handleDataIntegrityViolationException();

    assert response.getStatusCode() == HttpStatus.BAD_REQUEST;
    assert Objects.requireNonNull(response.getBody())
        .equals("Account with given username or email already exists");
  }

  @Test
  void testMethodArgumentNotValidExceptionHandling() {
    MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);
    when(ex.getAllErrors())
        .thenReturn(
            Arrays.asList(
                new FieldError("someObject", "someField", "someMessage"),
                new FieldError("someOtherObject", "someOtherField", "someOtherBindingResult")));

    ResponseEntity<List<String>> response =
        authController.handleMethodArgumentNotValidException(ex);

    assert response.getStatusCode() == HttpStatus.BAD_REQUEST;
    // Assert further based on the expected list of error messages
  }

  @Test
  void testAuthenticationExceptionHandling() {
    String errorMessage = "403 FORBIDDEN";

    AuthenticationException ex = mock(AuthenticationException.class, errorMessage);
    ResponseEntity<String> response = authController.authExceptionHandler(ex);
    HttpStatus status = (HttpStatus) response.getStatusCode();
    assert response.getStatusCode() == HttpStatus.FORBIDDEN;
    assertEquals(errorMessage, status.toString());
  }
}
