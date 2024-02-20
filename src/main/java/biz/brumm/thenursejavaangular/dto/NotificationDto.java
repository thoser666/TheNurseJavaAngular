package biz.brumm.thenursejavaangular.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author UrosVesic
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class NotificationDto implements Dto{
    private Long id;
    private Long postId;
    private String message;
    private boolean read;
}
