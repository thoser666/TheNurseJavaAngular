package biz.brumm.thenursejavaangular.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import biz.brumm.thenursejavaangular.dto.UserDto;
import biz.brumm.thenursejavaangular.exception.MyRuntimeException;
import biz.brumm.thenursejavaangular.mapper.ReportedUserMapper;
import biz.brumm.thenursejavaangular.mapper.UserMapper;
import biz.brumm.thenursejavaangular.model.Following;
import biz.brumm.thenursejavaangular.model.User;
import biz.brumm.thenursejavaangular.repository.FollowRepository;
import biz.brumm.thenursejavaangular.repository.PostReportRepository;
import biz.brumm.thenursejavaangular.repository.RoleRepository;
import biz.brumm.thenursejavaangular.repository.UserRepository;
import java.time.Instant;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceTest {

  @Mock private UserRepository userRepository;
  @Mock private AuthService authService;
  @Mock private RoleRepository roleRepository;
  @Mock private FollowRepository followRepository;
  @Mock private UserMapper userMapper;
  @Mock private PostReportRepository postReportRepository;
  @Mock private ReportedUserMapper reportedUserMapper;

  @InjectMocks private UserService userService;

  private User currentUser;
  private User userToFollow;

  @BeforeEach
  public void setUp() {
    // Initialize mocks and test data here
    currentUser = new User(/* initialize with test data */ );
    currentUser.setUserId(1L);
    currentUser.setBio("testBio");
    currentUser.setEnabled(true);
    currentUser.setCreated(Instant.now());
    currentUser.setUsername("testUser");
    currentUser.setEmail("testEmail");
    // currentUser.setRole(new Role(1,"Rolle", "details"));
    currentUser.setFollowers(null);
    currentUser.setFollowing(null);
    // currentUser.setPosts(null);
    // currentUser.setReportedUsers(null);
    // currentUser.setProfile(null);

    userToFollow = new User(/* initialize with test data */ );
    userToFollow.setUserId(1L);
    userToFollow.setBio("testBio");
    userToFollow.setEnabled(true);
    userToFollow.setCreated(Instant.now());
    userToFollow.setUsername("testUser");
    userToFollow.setEmail("testEmail");
    userToFollow.setFollowers(null);
    userToFollow.setFollowing(null);

    when(authService.getCurrentUser()).thenReturn(currentUser);
    when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(userToFollow));
    when(userRepository.findById(anyLong())).thenReturn(Optional.of(currentUser));
  }

  @Test
  void testFollow_UserFound_ShouldSaveFollowing() {
    // Given
    String usernameToFollow = "testUser";
    when(userRepository.findByUsername(usernameToFollow)).thenReturn(Optional.of(userToFollow));

    // When
    userService.follow(usernameToFollow);

    // Then
    verify(followRepository, times(1)).save(any(Following.class));
  }

  @Test
  void testFollow_SameUser_ShouldThrowException() {
    // Given
    String usernameToFollow = currentUser.getUsername();
    when(userRepository.findByUsername(usernameToFollow)).thenReturn(Optional.of(currentUser));

    // When & Then
    assertThrows(MyRuntimeException.class, () -> userService.follow(usernameToFollow));
  }

  @Test
  void testGetUser_UserFound_ShouldReturnUserDto() {
    // Given
    Long userId = 1L;
    User user = new User(/* initialize user */ );
    UserDto userDto = new UserDto(/* initialize userDto */ );
    when(userRepository.findById(userId)).thenReturn(Optional.of(user));
    when(userMapper.toDto(user)).thenReturn(userDto);

    // When
    UserDto result = userService.getUser(userId);

    // Then
    assertNotNull(result);
    assertEquals(userDto, result);
  }

  @Test
  void testGetUser_UserNotFound_ShouldThrowException() {
    // Given
    Long userId = 2L;
    when(userRepository.findById(userId)).thenReturn(Optional.empty());

    // When & Then
    assertThrows(MyRuntimeException.class, () -> userService.getUser(userId));
  }

  // Test methods will go here
}

// class UserServiceTest {
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
//    void unfollow() {
//    }
//
//    @Test
//    void getUser() {
//    }
//
//    @Test
//    void testUnfollow() {
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
//    void getProfileInfo() {
//    }
//
//    @Test
//    void getAllSuggestedUsers() {
//    }
//
//    @Test
//    void updateUser() {
//    }
//
//    @Test
//    void assignRole() {
//    }
//
//    @Test
//    void getReportedUsers() {
//    }
//
//    @Test
//    void disableUser() {
//    }
//
//    @Test
//    void enableUser() {
//    }
// }
