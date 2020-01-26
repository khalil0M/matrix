package com.humanup.matrix.bs.impl.sender;

import com.humanup.matrix.vo.PersonVO;
import com.humanup.matrix.vo.SkillVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class RabbitMQSkillSender {
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQSkillSender.class);

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQSkillSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value("${skill.queue.name}")
    String queueName;

    public void send(SkillVO skill) {
        LOGGER.info("Sending message... {} " ,skill.toString());
        rabbitTemplate.convertAndSend(queueName, skill);
    }

}
