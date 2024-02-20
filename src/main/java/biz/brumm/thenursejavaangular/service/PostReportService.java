package biz.brumm.thenursejavaangular.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import biz.brumm.thenursejavaangular.dto.PostReportRequest;
import biz.brumm.thenursejavaangular.exception.MyRuntimeException;
import biz.brumm.thenursejavaangular.mapper.PostReportRequestMapper;
import biz.brumm.thenursejavaangular.model.Post;
import biz.brumm.thenursejavaangular.model.PostReport;
import biz.brumm.thenursejavaangular.model.ReportStatus;
import biz.brumm.thenursejavaangular.repository.PostReportRepository;
import biz.brumm.thenursejavaangular.repository.PostRepository;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author UrosVesic
 */
@Service
@AllArgsConstructor
public class PostReportService {

    private PostReportRepository postReportRepository;
    private PostRepository postRepository;
    PostReportRequestMapper mapper;
    @Transactional
    public void reportPost(PostReportRequest request){
        PostReport postReport = mapper.toEntity(request);
        List<PostReport> postReportsByPost = postReportRepository.findByPost_id(request.getPostId());
        if(postReportsByPost.stream().anyMatch(report->
                (report.getReportStatus().equals(ReportStatus.APPROVED)
                        ||report.getReportStatus().equals(ReportStatus.DELETED)))){
            throw new MyRuntimeException("This post is already reviewed and approved");
        }
        postReportRepository.save(postReport);
    }
    @Transactional
    public void changeReportStatus(ReportStatus reportStatus,Long postId) {
        List<PostReport> reports = postReportRepository.findByPost_id(postId);
        reports.forEach(r->{
            if(r.getReportStatus().equals(reportStatus)){
                throw new MyRuntimeException("Already has status: "+reportStatus);
            }
            if(reportStatus.equals(ReportStatus.APPROVED)){
                Post post = postRepository.findById(postId).orElseThrow((() -> new MyRuntimeException("Post not found")));
                post.setDeletebByAdmin(null);
                postRepository.save(post);
            }
            r.setReportStatus(reportStatus);
        });
        postReportRepository.saveAll(reports);
    }
}
