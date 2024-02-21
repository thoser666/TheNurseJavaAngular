package biz.brumm.thenursejavaangular.mapper;

import biz.brumm.thenursejavaangular.dto.PostReportRequest;
import biz.brumm.thenursejavaangular.model.PostReport;
import biz.brumm.thenursejavaangular.model.ReportStatus;
import biz.brumm.thenursejavaangular.repository.PostRepository;
import biz.brumm.thenursejavaangular.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author UrosVesic
 */
@AllArgsConstructor
@NoArgsConstructor
public class PostReportRequestMapper implements GenericMapper<PostReportRequest, PostReport> {

  private PostRepository postRepository;
  private AuthService authService;

  @Override
  public PostReport toEntity(PostReportRequest dto) {
    PostReport postReport = new PostReport();
    postReport.setPost(postRepository.getById(dto.getPostId()));
    postReport.setUser(authService.getCurrentUser());
    postReport.setReportStatus(ReportStatus.UNSOLVED);
    return postReport;
  }

  @Override
  public PostReportRequest toDto(PostReport entity) {
    return null;
  }
}
