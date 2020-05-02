package com.humanup.matrix.bs.impl.listner;

import com.humanup.matrix.dao.SkillDAO;
import com.humanup.matrix.dao.TypeSkillsDAO;
import com.humanup.matrix.dao.entities.Skill;
import com.humanup.matrix.dao.entities.TypeSkills;
import com.humanup.matrix.vo.SkillVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSkillListner {
  private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQSkillListner.class);

  @Autowired private SkillDAO skillDAO;
  @Autowired private TypeSkillsDAO typeSkillsDAO;

  @RabbitListener(queues = {"${skill.queue.name}"})
  public void receive(SkillVO skillVO) {
    try {
      LOGGER.info("Receive  message... {} ", skillVO.toString());
      TypeSkills typeSkills = typeSkillsDAO.findByTitleSkill(skillVO.getTypeSkills());
      Skill skillToSave =
          Skill.builder()
              .libelle(skillVO.getLibelle())
              .description(skillVO.getDescription())
              .typeSkills(typeSkills)
              .build();
      skillDAO.save(skillToSave);
    } catch (Exception ex) {
      LOGGER.info("Error  message... {} ", ex.getMessage(), ex);
    }
  }
}
