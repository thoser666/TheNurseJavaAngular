package biz.brumm.thenursejavaangular.dto;

import biz.brumm.thenursejavaangular.model.ReactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author UrosVesic
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReactionDto implements Dto{
    private Long postId;
    private ReactionType reactionType;
}
