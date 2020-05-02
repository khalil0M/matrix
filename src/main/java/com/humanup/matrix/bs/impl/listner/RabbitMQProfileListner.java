package com.humanup.matrix.bs.impl.listner;

import com.humanup.matrix.dao.ProfileDAO;
import com.humanup.matrix.dao.entities.Profile;
import com.humanup.matrix.vo.ProfileVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProfileListner {
  private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQProfileListner.class);
  @Autowired private ProfileDAO profileDAO;

  @RabbitListener(queues = {"${profile.queue.name}"})
  public void receive(ProfileVO profileVO) {
    try {
      LOGGER.info("Receive  message... {} ", profileVO.toString());
      Profile profileToSave =
          Profile.builder()
              .profileTitle(profileVO.getProfileTitle())
              .profileDescription(profileVO.getProfileDescription())
              .build();
      profileDAO.save(profileToSave);
    } catch (Exception ex) {
      LOGGER.info("Error  message... {} ", ex.getMessage(), ex);
    }
  }
}
