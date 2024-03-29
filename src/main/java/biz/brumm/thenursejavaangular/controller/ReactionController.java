package biz.brumm.thenursejavaangular.controller;

import biz.brumm.thenursejavaangular.dto.ReactionDto;
import biz.brumm.thenursejavaangular.service.ReactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author UrosVesic
 */
@RestController
@RequestMapping("/api/react")
@AllArgsConstructor
public class ReactionController {

  private ReactionService reactionService;

  @PostMapping
  public ResponseEntity react(@RequestBody ReactionDto reactionDto) {
    reactionService.react(reactionDto);
    return new ResponseEntity(HttpStatus.CREATED);
  }
}
