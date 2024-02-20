package biz.brumm.thenursejavaangular.controller;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import biz.brumm.thenursejavaangular.dto.TopicDto;
import biz.brumm.thenursejavaangular.model.Topic;
import biz.brumm.thenursejavaangular.service.TopicService;

import java.util.List;

/**
 * @author UrosVesic
 */
@RestController
@RequestMapping("/api/topic")
@AllArgsConstructor

public class TopicController {

    private TopicService topicService;

    @GetMapping("/all")
    public ResponseEntity<List<TopicDto>> getAllTopics(){
    return new ResponseEntity<>(topicService.getAllTopics(), HttpStatus.OK);
    }
    @CrossOrigin
    @PostMapping("/create")
    public ResponseEntity createTopic(@RequestBody TopicDto topic){
        topicService.createTopic(topic);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public  ResponseEntity<String> handleDataIntegrityViolationException(){
        return new ResponseEntity<>("Topic with given name already exists",HttpStatus.BAD_REQUEST);
    }

}
