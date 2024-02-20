package biz.brumm.thenursejavaangular.mapper;

import lombok.AllArgsConstructor;
import org.ocpsoft.prettytime.PrettyTime;
import biz.brumm.thenursejavaangular.dto.CommentDto;
import biz.brumm.thenursejavaangular.exception.MyRuntimeException;
import biz.brumm.thenursejavaangular.model.Comment;
import biz.brumm.thenursejavaangular.repository.PostRepository;
import biz.brumm.thenursejavaangular.service.AuthService;

import java.time.Instant;

/**
 * @author UrosVesic
 */
@AllArgsConstructor
public class CommentMapper implements GenericMapper<CommentDto, Comment> {

    private PostRepository postRepository;
    private AuthService authService;

    @Override
    public Comment toEntity(CommentDto dto) {
        Comment comment = new Comment();
        comment.setPost(postRepository.findById(dto.getPostId()).orElseThrow(()->new MyRuntimeException("Post not found")));
        comment.setCreatedDate(Instant.now());
        comment.setText(dto.getText());
        comment.setUser(authService.getCurrentUser());
        return comment;
    }

    @Override
    public CommentDto toDto(Comment comment) {
        CommentDto dto = new CommentDto();
        dto.setId(comment.getId());
        dto.setPostId(comment.getPost().getId());
        dto.setUsername(comment.getUser().getUsername());
        dto.setText(comment.getText());
        PrettyTime p = new PrettyTime();
        dto.setDuration(p.format(comment.getCreatedDate()));
        return dto;
    }
}
