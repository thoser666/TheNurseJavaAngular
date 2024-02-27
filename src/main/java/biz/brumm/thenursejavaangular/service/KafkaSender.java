package biz.brumm.thenursejavaangular.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
@Service
public class KafkaSender
{
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    String kafkaTopic = "java_in_use_topic";

    public void send(String message) throws Exception {

        kafkaTemplate.send(kafkaTopic, message);
    }
}
