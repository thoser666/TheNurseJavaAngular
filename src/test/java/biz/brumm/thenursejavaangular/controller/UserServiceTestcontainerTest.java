package biz.brumm.thenursejavaangular.controller;

import biz.brumm.thenursejavaangular.provider.DBConnectionProvider;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;

class UserServiceTestcontainerTest {

  static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine");

  UserService userService;

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
    DBConnectionProvider connectionProvider =
        new DBConnectionProvider(
            postgres.getJdbcUrl(), postgres.getUsername(), postgres.getPassword());
    userService = new UserService(connectionProvider);
  }

  @Test
  void testFollow() {}

  @Test
  void testAssignRole() {}

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
