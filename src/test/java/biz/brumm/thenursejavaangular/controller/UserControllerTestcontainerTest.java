package biz.brumm.thenursejavaangular.controller;

import biz.brumm.thenursejavaangular.provider.DBConnectionProvider;
import biz.brumm.thenursejavaangular.service.AuthService;
import biz.brumm.thenursejavaangular.service.UserService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserControllerTestcontainerTest {

  static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
          "postgres:16-alpine"
  );

  UserController userController;

  @BeforeAll
  static void beforeAll() {
    postgres.start();
  }

  @AfterAll
  static void afterAll() {
    postgres.stop();
  }


  @BeforeEach
  void setUp() {
    DBConnectionProvider connectionProvider = new DBConnectionProvider(
            postgres.getJdbcUrl(),
            postgres.getUsername(),
            postgres.getPassword()
    );
    userController = new UserController(connectionProvider);
  }

  @Test
  void testFollow() {
  }

  @Test
  void testAssignRole() {
  }

  // Add more tests for other methods in a similar manner
}

// class UserControllerTest {
//
//    @BeforeEach
//    void setUp() {
//    }
//
//    @AfterEach
//    void tearDown() {
//    }
//
//    @Test
//    void follow() {
//    }
//
//    @Test
//    void assignRole() {
//    }
//
//    @Test
//    void unfollow() {
//    }
//
//    @Test
//    void testUnfollow() {
//    }
//
//    @Test
//    void getUser() {
//    }
//
//    @Test
//    void getProfileInfo() {
//    }
//
//    @Test
//    void getAllSuggestedUsers() {
//    }
//
//    @Test
//    void getAllReportedUsers() {
//    }
//
//    @Test
//    void disableUser() {
//    }
//
//    @Test
//    void enable() {
//    }
//
//    @Test
//    void getAllFollowersForUser() {
//    }
//
//    @Test
//    void getAllFollowingForUser() {
//    }
//
//    @Test
//    void updateUser() {
//    }
//
//    @Test
//    void handleMyRuntimeException() {
//    }
// }
