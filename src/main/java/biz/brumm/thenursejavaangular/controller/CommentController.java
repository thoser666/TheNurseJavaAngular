package biz.brumm.thenursejavaangular.controller;

import biz.brumm.thenursejavaangular.dto.CommentDto;
import biz.brumm.thenursejavaangular.service.CommentService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author UrosVesic
 */
@RestController
@RequestMapping("/api/comment")
@AllArgsConstructor
public class CommentController {

  private CommentService commentService;

  @PostMapping
  public ResponseEntity postComment(@RequestBody CommentDto commentDto) {
    commentService.comment(commentDto);
    return new ResponseEntity(HttpStatus.CREATED);
  }

  @GetMapping("/{postId}")
  public ResponseEntity<List<CommentDto>> getAllCommentsFormPost(@PathVariable Long postId) {
    List<CommentDto> commentDtos = commentService.getAllCommentsForPost(postId);
    return new ResponseEntity<>(commentDtos, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity deleteCommend(@PathVariable Long id) {
    commentService.deleteComment(id);
    return new ResponseEntity(HttpStatus.OK);
  }
}
