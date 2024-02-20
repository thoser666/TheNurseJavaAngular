package biz.brumm.thenursejavaangular.controller;

import biz.brumm.thenursejavaangular.dto.PostReportRequest;
import biz.brumm.thenursejavaangular.exception.MyRuntimeException;
import biz.brumm.thenursejavaangular.model.ReportStatus;
import biz.brumm.thenursejavaangular.service.PostReportService;
import java.sql.SQLIntegrityConstraintViolationException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author UrosVesic
 */
@RestController
@RequestMapping("/api/report")
@AllArgsConstructor
public class PostReportController {
  private PostReportService service;

  @PostMapping
  public ResponseEntity reportPost(@RequestBody PostReportRequest reportRequest) {
    service.reportPost(reportRequest);
    return new ResponseEntity(HttpStatus.OK);
  }

  @PatchMapping("/change-status/{postId}")
  public ResponseEntity changeReportStatus(
      @RequestBody ReportStatus reportStatus, @PathVariable Long postId) {
    service.changeReportStatus(reportStatus, postId);
    return new ResponseEntity(HttpStatus.OK);
  }

  @ExceptionHandler(MyRuntimeException.class)
  public ResponseEntity<String> handleMyRuntimeException(MyRuntimeException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
  public ResponseEntity<String> handleSQLIntegrityConstraintViolation(
      SQLIntegrityConstraintViolationException ex) {
    return new ResponseEntity<>("You have already reported this post", HttpStatus.BAD_REQUEST);
  }
}
