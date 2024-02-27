package biz.brumm.thenursejavaangular.controller;

// von https://www.javainuse.com/spring/spring-boot-apache-kafka-hello-world
import static org.junit.jupiter.api.Assertions.*;

import biz.brumm.thenursejavaangular.service.KafkaSender;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ApacheKafkaWebControllerTest {

  private KafkaSender sender;

  @BeforeEach
  void setUp() {
    sender = new KafkaSender();
  }

  @AfterEach
  void tearDown() {}

  @Test
  void producer() {
    // Assuming sender is an instance of your Sender class
    assertThrows(
        Exception.class,
        () -> {
          sender.send("Hello World");
        });
  }
}
