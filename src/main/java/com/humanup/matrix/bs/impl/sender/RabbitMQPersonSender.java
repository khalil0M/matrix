package com.humanup.matrix.bs.impl.sender;

import com.humanup.matrix.vo.PersonVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQPersonSender {
  private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQPersonSender.class);

  private final RabbitTemplate rabbitTemplate;

  public RabbitMQPersonSender(RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
  }

  @Value("${person.queue.name}")
  String queueName;

  public void send(PersonVO person) {
    rabbitTemplate.convertAndSend(queueName, person);
    LOGGER.info("Sending message... {} ", person.toString());
  }
}
