package biz.brumm.thenursejavaangular.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import biz.brumm.thenursejavaangular.service.AuthService;
import biz.brumm.thenursejavaangular.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class UserControllerTest {

  @Mock private UserService userService;

  @Mock private AuthService authService;

  @InjectMocks private UserController userController;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void testFollow() {
    String username = "testUser";
    ResponseEntity expectedResponse = new ResponseEntity<>(HttpStatus.CREATED);

    when(userService.follow(username)).thenReturn(true);

    ResponseEntity response = userController.follow(username);

    verify(userService, times(1)).follow(username);
    assertEquals(expectedResponse, response);
  }

  @Test
  void testAssignRole() {
    String username = "testUser";
    String rolename = "admin";
    ResponseEntity expectedResponse = new ResponseEntity<>(HttpStatus.OK);

    when(userService.assignRole(username, rolename)).thenReturn(true);

    ResponseEntity response = userController.assignRole(username, rolename);

    verify(userService, times(1)).assignRole(username, rolename);
    assertEquals(expectedResponse, response);
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
