package biz.brumm.thenursejavaangular;

import biz.brumm.thenursejavaangular.config.AppConfig;
import biz.brumm.thenursejavaangular.config.RsaKeyProperties;
import biz.brumm.thenursejavaangular.dto.Greeting;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

@Log
@SpringBootApplication
@EnableConfigurationProperties({AppConfig.class, RsaKeyProperties.class})
public class TheNurseJavaAngularApplication {

  public static void main(String[] args) {
    ConfigurableApplicationContext context =
        SpringApplication.run(TheNurseJavaAngularApplication.class, args);

    // Download & start Kafka
    DefaultDockerClientConfig.Builder config =
        DefaultDockerClientConfig.createDefaultConfigBuilder();
    DockerClient dockerClient = DockerClientBuilder.getInstance(config).build();

    MessageProducer producer = context.getBean(MessageProducer.class);
    MessageListener listener = context.getBean(MessageListener.class);
    /*
     * Sending a Hello World message to topic 'baeldung'.
     * Must be received by both listeners with group foo
     * and bar with containerFactory fooKafkaListenerContainerFactory
     * and barKafkaListenerContainerFactory respectively.
     * It will also be received by the listener with
     * headersKafkaListenerContainerFactory as container factory.
     */
    producer.sendMessage("Hello, World!");
    try {
      if (!listener.latch.await(10, TimeUnit.SECONDS)) {
        throw new TimeoutException("Timed out while waiting for latch");
      }
    } catch (InterruptedException | TimeoutException e) {
      Thread.currentThread().interrupt();
      throw new RuntimeException("Interrupted while waiting for latch", e);
    }

    /*
     * Sending message to a topic with 5 partitions,
     * each message to a different partition. But as per
     * listener configuration, only the messages from
     * partition 0 and 3 will be consumed.
     */
    for (int i = 0; i < 5; i++) {
      producer.sendMessageToPartition("Hello To Partitioned Topic!", i);
    }
    try {
      if (!listener.partitionLatch.await(10, TimeUnit.SECONDS)) {
        throw new TimeoutException("Timed out while waiting for partition latch");
      }
    } catch (InterruptedException | TimeoutException e) {
      Thread.currentThread().interrupt();
      throw new RuntimeException("Interrupted while waiting for partition latch", e);
    }

    /*
     * Sending message to 'filtered' topic. As per listener
     * configuration,  all messages with char sequence
     * 'World' will be discarded.
     */
    producer.sendMessageToFiltered("Hello Baeldung!");
    producer.sendMessageToFiltered("Hello World!");
    try {
      if (!listener.filterLatch.await(10, TimeUnit.SECONDS)) {
        throw new TimeoutException("Timed out while waiting for filter latch");
      }
    } catch (InterruptedException | TimeoutException e) {
      Thread.currentThread().interrupt();
      throw new RuntimeException("Interrupted while waiting for filter latch", e);
    }

    /*
     * Sending message to 'greeting' topic. This will send
     * and received a java object with the help of
     * greetingKafkaListenerContainerFactory.
     */
    producer.sendGreetingMessage(new Greeting("Greetings", "World!"));
    try {
      if (!listener.greetingLatch.await(10, TimeUnit.SECONDS)) {
        throw new TimeoutException("Timed out while waiting for greeting latch");
      }
    } catch (InterruptedException | TimeoutException e) {
      Thread.currentThread().interrupt();
      throw new RuntimeException("Interrupted while waiting for greeting latch", e);
    }

    context.close();
  }

  @Bean
  public MessageProducer messageProducer() {
    return new MessageProducer();
  }

  @Bean
  public MessageListener messageListener() {
    return new MessageListener();
  }

  public static class MessageProducer {

    @Autowired private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired private KafkaTemplate<String, Greeting> greetingKafkaTemplate;

    @Value(value = "${message.topic.name}")
    private String topicName;

    @Value(value = "${partitioned.topic.name}")
    private String partitionedTopicName;

    @Value(value = "${filtered.topic.name}")
    private String filteredTopicName;

    @Value(value = "${greeting.topic.name}")
    private String greetingTopicName;

    public void sendMessage(String message) {

      CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(topicName, message);
      future.whenComplete(
          (result, ex) -> {
            if (ex == null) {
              System.out.println(
                  "Sent message=["
                      + message
                      + "] with offset=["
                      + result.getRecordMetadata().offset()
                      + "]");
            } else {
              System.out.println(
                  "Unable to send message=[" + message + "] due to : " + ex.getMessage());
            }
          });
    }

    public void sendMessageToPartition(String message, int partition) {
      kafkaTemplate.send(partitionedTopicName, partition, null, message);
    }

    public void sendMessageToFiltered(String message) {
      kafkaTemplate.send(filteredTopicName, message);
    }

    public void sendGreetingMessage(Greeting greeting) {
      greetingKafkaTemplate.send(greetingTopicName, greeting);
    }
  }

  public static class MessageListener {

    private final CountDownLatch latch = new CountDownLatch(3);

    private final CountDownLatch partitionLatch = new CountDownLatch(2);

    private final CountDownLatch filterLatch = new CountDownLatch(2);

    private final CountDownLatch greetingLatch = new CountDownLatch(1);

    @KafkaListener(
        topics = "${message.topic.name}",
        groupId = "foo",
        containerFactory = "fooKafkaListenerContainerFactory")
    public void listenGroupFoo(String message) {
      System.out.println("Received Message in group 'foo': " + message);
      latch.countDown();
    }

    @KafkaListener(
        topics = "${message.topic.name}",
        groupId = "bar",
        containerFactory = "barKafkaListenerContainerFactory")
    public void listenGroupBar(String message) {
      System.out.println("Received Message in group 'bar': " + message);
      latch.countDown();
    }

    @KafkaListener(
        topics = "${message.topic.name}",
        containerFactory = "headersKafkaListenerContainerFactory")
    public void listenWithHeaders(
        @Payload String message, @Header(KafkaHeaders.RECEIVED_PARTITION) int partition) {
      System.out.println("Received Message: " + message + " from partition: " + partition);
      latch.countDown();
    }

    @KafkaListener(
        topicPartitions =
            @TopicPartition(
                topic = "${partitioned.topic.name}",
                partitions = {"0", "3"}),
        containerFactory = "partitionsKafkaListenerContainerFactory")
    public void listenToPartition(
        @Payload String message, @Header(KafkaHeaders.RECEIVED_PARTITION) int partition) {
      System.out.println("Received Message: " + message + " from partition: " + partition);
      this.partitionLatch.countDown();
    }

    @KafkaListener(
        topics = "${filtered.topic.name}",
        containerFactory = "filterKafkaListenerContainerFactory")
    public void listenWithFilter(String message) {
      System.out.println("Received Message in filtered listener: " + message);
      this.filterLatch.countDown();
    }

    @KafkaListener(
        topics = "${greeting.topic.name}",
        containerFactory = "greetingKafkaListenerContainerFactory")
    public void greetingListener(Greeting greeting) {
      System.out.println("Received greeting message: " + greeting);
      this.greetingLatch.countDown();
    }
  }
}
