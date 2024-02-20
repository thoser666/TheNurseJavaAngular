package biz.brumm.thenursejavaangular.mapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ocpsoft.prettytime.PrettyTime;
import biz.brumm.thenursejavaangular.dto.ReportedPostDto;
import biz.brumm.thenursejavaangular.model.Post;
import biz.brumm.thenursejavaangular.model.PostReport;
import biz.brumm.thenursejavaangular.model.ReportStatus;
import biz.brumm.thenursejavaangular.repository.PostReportRepository;

import java.util.Optional;

/**
 * @author UrosVesic
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportedPostMapper implements GenericMapper<ReportedPostDto, Post> {

    private PostReportRepository reportRepository;
    @Override
    public Post toEntity(ReportedPostDto dto) {
        return null;
    }

    @Override
    public ReportedPostDto toDto(Post entity) {
       ReportedPostDto dto = new ReportedPostDto();
       dto.setId(entity.getId());
       dto.setContent(entity.getContent());
       dto.setTitle(entity.getTitle());
       PrettyTime p = new PrettyTime();
       dto.setDuraton(p.format(entity.getCreatedDate()));
       dto.setTopicname(entity.getTopic().getName());
       dto.setUsername(entity.getUser().getUsername());
       dto.setReportCount(getReportCount(entity));
       dto.setReportStatus(setReportStatus(entity));
        return dto;
    }

    private ReportStatus setReportStatus(Post post) {
        Optional<PostReport> report = reportRepository.findFirstByPost(post);
        return report.get().getReportStatus();
    }

    private int getReportCount(Post post) {
        return reportRepository.countByPost(post).intValue();
    }
}
