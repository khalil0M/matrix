package com.humanup.matrix.bs.impl;

import com.humanup.matrix.bs.TypeSkillsBS;
import com.humanup.matrix.bs.impl.sender.RabbitMQTypeSkillSender;
import com.humanup.matrix.dao.TypeSkillsDAO;
import com.humanup.matrix.dao.entities.TypeSkills;
import com.humanup.matrix.vo.TypeSkillsVO;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class TypeSkillsBSImpl implements TypeSkillsBS {

  @Autowired private TypeSkillsDAO typeSkillsDAO;

  @Autowired RabbitMQTypeSkillSender rabbitMQTypeSkillSender;

  @Override
  @Transactional(transactionManager = "transactionManagerWrite")
  public boolean createTypeSkills(TypeSkillsVO typeSkillsVO) {
    if (null == typeSkillsVO) return false;
    rabbitMQTypeSkillSender.send(typeSkillsVO);
    return true;
  }

  @Override
  public TypeSkillsVO findByTypeSkillsTitle(String titleSkill) {
    Optional<TypeSkills> typeSkillsFinded =
        Optional.ofNullable(typeSkillsDAO.findByTitleSkill(titleSkill));
    if (typeSkillsFinded.isPresent()) {
      return TypeSkillsVO.builder().titleSkill(typeSkillsFinded.get().getTitleSkill()).build();
    }
    return null;
  }

  @Override
  public TypeSkillsVO findByTypeSkillsByID(Long id) {
    Optional<TypeSkills> typeSkillsFinded = Optional.ofNullable(typeSkillsDAO.findByTypeId(id));
    if (typeSkillsFinded.isPresent()) {
      return TypeSkillsVO.builder().titleSkill(typeSkillsFinded.get().getTitleSkill()).build();
    }
    return null;
  }

  @Override
  public List<TypeSkillsVO> findListTypeSkills() {
    return typeSkillsDAO.findAll().stream()
        .map(
            typeSkillsFinded ->
                TypeSkillsVO.builder().titleSkill(typeSkillsFinded.getTitleSkill()).build())
        .collect(Collectors.toList());
  }

  @Override
  public List<TypeSkillsVO> findListTypeSkillsByTitle(String title) {
    Optional<List<TypeSkills>> listTypeSkillsFinded =
        Optional.ofNullable(typeSkillsDAO.findTypeSkillsByTitle(title));
    if (listTypeSkillsFinded.isPresent()) {
      return listTypeSkillsFinded.get().stream()
          .map(
              typeSkillsFinded ->
                  TypeSkillsVO.builder().titleSkill(typeSkillsFinded.getTitleSkill()).build())
          .collect(Collectors.toList());
    }
    return null;
  }
}
