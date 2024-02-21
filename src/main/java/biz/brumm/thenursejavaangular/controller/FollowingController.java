package biz.brumm.thenursejavaangular.controller;

import biz.brumm.thenursejavaangular.dto.UserDto;
import biz.brumm.thenursejavaangular.service.FollowingService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author UrosVesic
 */
@RestController
@RequestMapping("/api/following")
@AllArgsConstructor
public class FollowingController {

  private FollowingService followingService;

  @GetMapping("/followers/{userId}")
  public List<UserDto> getFollowersForUser(@PathVariable Long userId) {
    return followingService.getFollowersForUser(userId);
  }
}
