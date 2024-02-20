package biz.brumm.thenursejavaangular.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author UrosVesic
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostReportRequest implements Dto{
    private Long postId;
    private String username;

}
