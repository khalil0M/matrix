package com.humanup.matrix.bs.impl.sender;

import com.humanup.matrix.vo.PersonVO;
import com.humanup.matrix.vo.TypeSkillsVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class RabbitMQTypeSkillSender {
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQTypeSkillSender.class);

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQTypeSkillSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value("${typeskill.queue.name}")
    String queueName;

    public void send(TypeSkillsVO typeSkills) {
        LOGGER.info("Sending message... {} " ,typeSkills.toString());
        rabbitTemplate.convertAndSend(queueName, typeSkills);
    }

}
