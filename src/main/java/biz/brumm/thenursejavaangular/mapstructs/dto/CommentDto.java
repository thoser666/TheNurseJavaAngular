package biz.brumm.thenursejavaangular.mapstructs.dto;

import lombok.Data;

@Data
public class CommentDto {
  private Long id;
  private Long postId;
  private String text;
  private String username;
  private String duration;
}
