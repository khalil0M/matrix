package com.humanup.matrix.bs.impl.listner;

import com.humanup.matrix.dao.PersonDAO;
import com.humanup.matrix.dao.ProfileDAO;
import com.humanup.matrix.dao.entities.Person;
import com.humanup.matrix.dao.entities.Profile;
import com.humanup.matrix.vo.PersonVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@RefreshScope
public class RabbitMQPersonListner {
  private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQPersonListner.class);

  @Autowired private PersonDAO personDAO;
  @Autowired private ProfileDAO profileDAO;

  @RabbitListener(queues = {"${person.queue.name}"})
  public void receive(PersonVO personVO) {
    try {
      LOGGER.info("Receive  message... {} ", personVO.toString());
      Profile profile = profileDAO.findByProfileTitle(personVO.getProfile());
      String email = personVO.getMailAdresses();

      if (null == profile || null == email || StringUtils.isEmpty(email)) {
        LOGGER.info("Received message as generic: {}", personVO.toString());
      }

      Person personToSave =
          Person.builder()
              .firstName(personVO.getFirstName())
              .lastName(personVO.getLastName())
              .mailAdresses(personVO.getMailAdresses())
              .birthDate(personVO.getBirthDate())
              .profile(profile)
              .build();
      personDAO.save(personToSave);
    } catch (Exception ex) {
      LOGGER.info("Error  message... {} ", ex.getMessage(), ex);
    }
  }

  @RabbitListener(queues = {"${person.queue.name}"})
  public void receivePersonSkill(Person person) {
    try {
      LOGGER.info("Receive  message... {} ", person.toString());
      personDAO.save(person);
    } catch (Exception ex) {
      LOGGER.info("Error  message... {} ", ex.getMessage(), ex);
    }
  }
}
