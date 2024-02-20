package biz.brumm.thenursejavaangular.mapper;

import lombok.AllArgsConstructor;
import biz.brumm.thenursejavaangular.dto.TopicDto;
import biz.brumm.thenursejavaangular.model.Topic;
import biz.brumm.thenursejavaangular.repository.PostRepository;
import biz.brumm.thenursejavaangular.service.AuthService;

import java.time.Instant;

/**
 * @author UrosVesic
 */
@AllArgsConstructor
public class TopicMapper implements GenericMapper<TopicDto, Topic> {

    private PostRepository postRepository;
    private AuthService authService;

    @Override
    public Topic toEntity(TopicDto dto) {
        Topic topic = new Topic();
        topic.setDescription(dto.getDescription());
        topic.setName(dto.getName());
        topic.setCreatedDate(Instant.now());
        topic.setUser(authService.getCurrentUser());
        return topic;
    }

    @Override
    public TopicDto toDto(Topic topic) {
        TopicDto dto = new TopicDto();
        dto.setDescription(topic.getDescription());
        dto.setId(topic.getId());
        dto.setName(topic.getName());
        dto.setNumberOfPosts(postRepository.findByTopicAndDeletebByAdminIsNull(topic).size());
        return dto;
    }
}
