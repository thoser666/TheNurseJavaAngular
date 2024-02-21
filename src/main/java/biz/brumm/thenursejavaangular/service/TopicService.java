package biz.brumm.thenursejavaangular.service;

import biz.brumm.thenursejavaangular.dto.TopicDto;
import biz.brumm.thenursejavaangular.mapper.TopicMapper;
import biz.brumm.thenursejavaangular.model.Topic;
import biz.brumm.thenursejavaangular.repository.TopicRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author UrosVesic
 */
@Service
@AllArgsConstructor
public class TopicService {
  private TopicRepository topicRepository;
  private TopicMapper topicMapper;

  @Transactional
  public List<TopicDto> getAllTopics() {
    List<Topic> topics = topicRepository.findAll();
    return topics.stream().map((topic) -> topicMapper.toDto(topic)).collect(Collectors.toList());
  }

  @Transactional
  public void createTopic(TopicDto dto) {
    Topic topic = topicMapper.toEntity(dto);
    topicRepository.save(topic);
  }
}
