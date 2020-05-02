package com.humanup.matrix.bs.impl.listner;

import com.humanup.matrix.dao.TypeSkillsDAO;
import com.humanup.matrix.dao.entities.TypeSkills;
import com.humanup.matrix.vo.TypeSkillsVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQTypeSkillListner {
  private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQTypeSkillListner.class);
  @Autowired private TypeSkillsDAO typeSkillsDAO;

  @RabbitListener(queues = {"${typeskill.queue.name}"})
  public void receive(TypeSkillsVO typeSkillsVO) {
    try {
      LOGGER.info("Receive  message... {} ", typeSkillsVO.toString());
      TypeSkills typeToSave = TypeSkills.builder().titleSkill(typeSkillsVO.getTitleSkill()).build();
      typeSkillsDAO.save(typeToSave);
    } catch (Exception ex) {
      LOGGER.info("Error  message... {} ", ex.getMessage(), ex);
    }
  }
}
