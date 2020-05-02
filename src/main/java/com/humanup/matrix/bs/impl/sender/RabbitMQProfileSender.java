package com.humanup.matrix.bs.impl.sender;

import com.humanup.matrix.vo.ProfileVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProfileSender {
  private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQProfileSender.class);

  private final RabbitTemplate rabbitTemplate;

  public RabbitMQProfileSender(RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
  }

  @Value("${profile.queue.name}")
  String queueName;

  public void send(ProfileVO profile) {
    LOGGER.info("Sending message... {} ", profile.toString());
    rabbitTemplate.convertAndSend(queueName, profile);
  }
}
