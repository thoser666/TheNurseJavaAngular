package biz.brumm.thenursejavaangular.mapper;

import biz.brumm.thenursejavaangular.dto.ReactionDto;
import biz.brumm.thenursejavaangular.exception.MyRuntimeException;
import biz.brumm.thenursejavaangular.model.Reaction;
import biz.brumm.thenursejavaangular.repository.PostRepository;
import biz.brumm.thenursejavaangular.service.AuthService;
import lombok.AllArgsConstructor;

/**
 * @author UrosVesic
 */
@AllArgsConstructor
public class ReactionMapper implements GenericMapper<ReactionDto, Reaction> {

  private PostRepository postRepository;
  private AuthService authService;

  @Override
  public Reaction toEntity(ReactionDto dto) {
    Reaction reaction = new Reaction();
    reaction.setPost(
        postRepository
            .findById(dto.getPostId())
            .orElseThrow(() -> new MyRuntimeException("Post not found")));
    reaction.setReactionType(dto.getReactionType());
    reaction.setUser(authService.getCurrentUser());
    return reaction;
  }

  @Override
  public ReactionDto toDto(Reaction entity) {
    return null;
  }
}
