package biz.brumm.thenursejavaangular.controller;

import biz.brumm.thenursejavaangular.service.KafkaSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/javainuse-kafka/")
public class ApacheKafkaWebController {
    @Autowired
    KafkaSender kafkaSender;

    @GetMapping(value = "/producer")
    public String producer(@RequestParam("message") String message) throws Exception {
        kafkaSender.send(message);

  @GetMapping(value = "/producer")
  public String producer(@RequestParam("message") String message) {
    kafkaSender.send(message);

    return "Message sent to the Kafka Topic java_in_use_topic Successfully";
  }
}
