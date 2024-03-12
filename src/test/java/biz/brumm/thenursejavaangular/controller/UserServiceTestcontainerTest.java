package biz.brumm.thenursejavaangular.controller;

import biz.brumm.thenursejavaangular.provider.DBConnectionProvider;
import org.junit.jupiter.api.*;
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
  void testFollow() {
    Assertions.assertTrue(userService.follow("testUser").hasBody());
  }

  @Test
  void testAssignRole() {
    Assertions.assertTrue(userService.assignRole("testUser", "admin").hasBody());
  }

  // Add more tests for other methods in a similar manner
}
