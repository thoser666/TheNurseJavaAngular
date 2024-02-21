package biz.brumm.thenursejavaangular.service;

import biz.brumm.thenursejavaangular.dto.ReactionDto;
import biz.brumm.thenursejavaangular.mapper.NotificationBuilder;
import biz.brumm.thenursejavaangular.mapper.ReactionMapper;
import biz.brumm.thenursejavaangular.model.Notification;
import biz.brumm.thenursejavaangular.model.Reaction;
import biz.brumm.thenursejavaangular.repository.ReactionRepository;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author UrosVesic
 */
@Service
@AllArgsConstructor
public class ReactionService {

  private ReactionRepository reactionRepository;
  private ReactionMapper reactionMapper;
  private AuthService authService;
  private NotificationService notificationService;
  private NotificationBuilder notificationBuilder;

  @Transactional
  public void react(ReactionDto reactionDto) {
    Optional<Reaction> reactOpt =
        reactionRepository.findByPost_idAndUser(
            reactionDto.getPostId(), authService.getCurrentUser());
    Reaction reactionEntity = reactionMapper.toEntity(reactionDto);
    if (!reactOpt.isPresent()) {
      reactionRepository.save(reactionEntity);
      Notification not = notificationBuilder.createNotificationForReaction(reactionEntity);
      notificationService.save(not);
      return;
    }
    Reaction reaction = reactOpt.get();
    if (reaction.getReactionType() == reactionDto.getReactionType()) {
      reactionRepository.delete(reaction);
    } else {
      reactionRepository.delete(reaction);
      reactionRepository.save(reactionEntity);
      Notification not = notificationBuilder.createNotificationForReaction(reactionEntity);
      notificationService.save(not);
    }
  }
}
