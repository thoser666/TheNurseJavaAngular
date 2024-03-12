package biz.brumm.thenursejavaangular.controller;

import biz.brumm.thenursejavaangular.dto.ReportedUserDto;
import biz.brumm.thenursejavaangular.dto.UserDto;
import biz.brumm.thenursejavaangular.exception.ErrorResponse;
import biz.brumm.thenursejavaangular.exception.MyRuntimeException;
import biz.brumm.thenursejavaangular.provider.DBConnectionProvider;
import biz.brumm.thenursejavaangular.service.AuthService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author UrosVesic
 */
@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserService {

  private biz.brumm.thenursejavaangular.service.UserService userService;
  private AuthService authService;
  private final DBConnectionProvider connectionProvider;

  public UserService(DBConnectionProvider connectionProvider) {
    this.connectionProvider = connectionProvider;
  }

  @PostMapping(value = "/follow/{username}")
  public ResponseEntity follow(@PathVariable String username) {
    userService.follow(username);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @PostMapping("/{username}/assign/{rolename}")
  public ResponseEntity assignRole(@PathVariable String username, @PathVariable String rolename) {
    userService.assignRole(username, rolename);
    return new ResponseEntity(HttpStatus.OK);
  }

  @PostMapping("/unfollow/{username}")
  public ResponseEntity unfollow(@PathVariable String username) {
    userService.unfollow(authService.getCurrentUser(), username);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @DeleteMapping("/{idFollowing}/unfollow/{idFollowed}")
  public ResponseEntity unfollow(@PathVariable Long idFollowing, @PathVariable Long idFollowed) {
    userService.unfollow(idFollowing, idFollowed);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
    UserDto userDto = userService.getUser(id);
    return new ResponseEntity<>(userDto, HttpStatus.OK);
  }

  /* @GetMapping("/followers/{userId}")
  public ResponseEntity<List<UserDto>> getAllFollowersForUser(@PathVariable Long userId){
      return new ResponseEntity<>(userService.getAllFollowersForUser(userId),HttpStatus.OK);
  }*/

  @GetMapping("/profile-info/{username}")
  public ResponseEntity<UserDto> getProfileInfo(@PathVariable String username) {
    UserDto userResponse = userService.getProfileInfo(username);
    return new ResponseEntity<UserDto>(userResponse, HttpStatus.OK);
  }

  @GetMapping("/suggested")
  public ResponseEntity<List<UserDto>> getAllSuggestedUsers() {
    List<UserDto> users = userService.getAllSuggestedUsers();
    return new ResponseEntity<>(users, HttpStatus.OK);
  }

  @GetMapping("/reported")
  public ResponseEntity<List<ReportedUserDto>> getAllReportedUsers() {
    List<ReportedUserDto> reportedUserDtos = userService.getReportedUsers();
    return new ResponseEntity<>(reportedUserDtos, HttpStatus.OK);
  }

  @PatchMapping("/disable/{username}")
  public ResponseEntity disableUser(@PathVariable String username) {
    userService.disableUser(username);
    return new ResponseEntity(HttpStatus.OK);
  }

  @PatchMapping("/enable/{username}")
  public ResponseEntity enable(@PathVariable String username) {
    userService.enableUser(username);
    return new ResponseEntity(HttpStatus.OK);
  }

  @GetMapping("/followers/{username}")
  public ResponseEntity<List<UserDto>> getAllFollowersForUser(@PathVariable String username) {
    return new ResponseEntity<>(userService.getAllFollowersForUser(username), HttpStatus.OK);
  }

  @GetMapping("/following/{username}")
  public ResponseEntity<List<UserDto>> getAllFollowingForUser(@PathVariable String username) {
    return new ResponseEntity<>(userService.getAllFollowingForUser(username), HttpStatus.OK);
  }

  @PatchMapping
  public ResponseEntity updateUser(@RequestBody UserDto userDto) {
    userService.updateUser(userDto);
    return new ResponseEntity(HttpStatus.OK);
  }

  @ExceptionHandler(MyRuntimeException.class)
  public ResponseEntity<ErrorResponse> handleMyRuntimeException(MyRuntimeException ex) {
    ErrorResponse error = new ErrorResponse(ex.getMessage());
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }
}
