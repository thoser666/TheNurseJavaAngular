package biz.brumm.thenursejavaangular.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import biz.brumm.thenursejavaangular.dto.UserDto;
import biz.brumm.thenursejavaangular.mapper.UserMapper;
import biz.brumm.thenursejavaangular.model.Following;
import biz.brumm.thenursejavaangular.model.User;
import biz.brumm.thenursejavaangular.repository.FollowRepository;
import biz.brumm.thenursejavaangular.repository.UserRepository;

import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author UrosVesic
 */
@Service
@AllArgsConstructor
public class FollowingService {

    private FollowRepository followRepository;
    private UserMapper userMapper;

    @Transactional
    public List<UserDto> getFollowersForUser(Long userId) {
        List<Following> optFoll = followRepository.findAllByFollowed_userId(userId);
        List<User> followers = optFoll.stream().map(Following::getFollowing).toList();
        List<UserDto> followersDto= followers.stream().map((userToMap)->userMapper.toDto(userToMap)).collect(Collectors.toList());
        return followersDto;
    }
}
