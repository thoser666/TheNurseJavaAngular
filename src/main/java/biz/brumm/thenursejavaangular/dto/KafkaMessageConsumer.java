package biz.brumm.thenursejavaangular.dto;

import biz.brumm.thenursejavaangular.partitioningstrategy.ReceivedMessage;
import jakarta.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageConsumer {

  private final List<ReceivedMessage> receivedMessages = new CopyOnWriteArrayList<>();

  @KafkaListener(
      topics = {"my-topic"},
      groupId = "my-consumer-group")
  public void listen(@Payload String message, @Headers Map<String, Object> headers) {
    System.out.println("Received message: " + message);
    System.out.println("Headers:");
    headers.forEach((key, value) -> System.out.println(key + ": " + value));

    String topicName = (String) headers.get(KafkaHeaders.TOPIC);
    System.out.println("Topic: " + topicName);
    int partitionID = (int) headers.get(KafkaHeaders.RECEIVED_PARTITION);
    System.out.println("Partition ID: " + partitionID);
  }

  @KafkaListener(
      topics = {"my-topic"},
      groupId = "my-consumer-group")
  public void listen(
      @Payload String message,
      @Header(KafkaHeaders.RECEIVED_TOPIC) String topicName,
      @Header(KafkaHeaders.RECEIVED_PARTITION) int partition) {
    System.out.println("Topic: " + topicName);
    System.out.println("Partition ID: " + partition);
  }

  public void listen(
      @Payload String message,
      @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
      @Header(KafkaHeaders.RECEIVED_KEY) @Nullable String key) {
    ReceivedMessage receivedMessage = new ReceivedMessage(key, message, partition);
    System.out.println("Received message: " + receivedMessage);
    receivedMessages.add(receivedMessage);
  }

  public List<ReceivedMessage> getReceivedMessages() {
    return receivedMessages;
  }

  public void clearReceivedMessages() {
    receivedMessages.clear();
  }
}
