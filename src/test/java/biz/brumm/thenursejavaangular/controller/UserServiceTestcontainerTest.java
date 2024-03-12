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


