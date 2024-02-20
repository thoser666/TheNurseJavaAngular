package biz.brumm.thenursejavaangular.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import biz.brumm.thenursejavaangular.dto.CommentDto;
import biz.brumm.thenursejavaangular.mapper.CommentMapper;
import biz.brumm.thenursejavaangular.mapper.NotificationBuilder;
import biz.brumm.thenursejavaangular.model.Comment;
import biz.brumm.thenursejavaangular.model.Notification;
import biz.brumm.thenursejavaangular.repository.CommentRepository;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author UrosVesic
 */
@Service
@AllArgsConstructor
public class CommentService {
    private CommentRepository commentRepository;
    private CommentMapper commentMapper;
    private NotificationService notificationService;
    private NotificationBuilder notificationBuilder;
    @Transactional
    public void comment(CommentDto commentDto){
        Comment comment = commentMapper.toEntity(commentDto);
        commentRepository.save(comment);
        Notification notification = notificationBuilder.createNotificationForComment(comment);
        notificationService.save(notification);
    }
    @Transactional
    public List<CommentDto> getAllCommentsForPost(Long postId) {
        List<Comment> comments = commentRepository.findByPost_idOrderByCreatedDateDesc(postId);
        return comments.stream().map((comment)->commentMapper.toDto(comment)).collect(Collectors.toList());
    }
    @Transactional
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}
