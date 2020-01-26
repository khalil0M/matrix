package com.humanup.matrix.bs.impl;

import com.humanup.matrix.bs.PersonBS;
import com.humanup.matrix.bs.impl.sender.RabbitMQPersonSender;
import com.humanup.matrix.dao.PersonDAO;
import com.humanup.matrix.dao.ProfileDAO;
import com.humanup.matrix.dao.SkillDAO;
import com.humanup.matrix.dao.entities.Person;
import com.humanup.matrix.dao.entities.Profile;
import com.humanup.matrix.dao.entities.Skill;
import com.humanup.matrix.dao.entities.TypeSkills;
import com.humanup.matrix.exceptions.ProfileException;
import com.humanup.matrix.vo.PersonVO;
import com.humanup.matrix.vo.SkillVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@Transactional(readOnly = true)
public class PersonBSImpl implements PersonBS {
  private static final Logger LOGGER = LoggerFactory.getLogger(PersonBSImpl.class);

  @Autowired
  private PersonDAO personDAO;

  @Autowired
  private ProfileDAO profileDAO;

  @Autowired
  private SkillDAO skillDAO;

  @Autowired
  RabbitMQPersonSender rabbitMQPersonSender;

  @Override
  @Transactional(transactionManager="transactionManagerWrite",rollbackFor = ProfileException.class)
  public boolean createPerson(PersonVO personVO) {
    if(null==personVO)return false;
    rabbitMQPersonSender.send(personVO);
    return  true;
  }

  @Override
  @Transactional(transactionManager="transactionManagerWrite",rollbackFor = ProfileException.class)
  public boolean addSkillsPerson(List<Integer> skills,String email) throws ProfileException {
    Person personToUpdate = personDAO.findByMailAdresses(email);
    if(null == personToUpdate || null == email || StringUtils.isEmpty(email)){
      throw new ProfileException();
    }
    Set<Skill> collected = skills.stream().map(id -> skillDAO.findById(id)).collect(Collectors.toSet());
    Set<Skill> personSkills = personToUpdate.getSkills();
    if(!personSkills.isEmpty()){
      personSkills.addAll(collected);
    }else{
      personToUpdate.setSkills(collected);
    }
    return personDAO.save(personToUpdate)!=null;
  }

  @Override
  public PersonVO findPersonByMailAdresses(String mailAdresses) {
    Optional<Person>  personFinded = Optional.ofNullable(personDAO.findByMailAdresses(mailAdresses));
    if(personFinded.isPresent()) {
      return PersonVO.builder()
          .mailAdresses(personFinded.get().getMailAdresses())
          .birthDate(personFinded.get().getBirthDate())
          .firstName(personFinded.get().getFirstName())
          .lastName(personFinded.get().getLastName())
              .profile(personFinded.get().getProfile().getProfileTitle())
              .skillVOList(personFinded.get().getSkills().stream()
              .map(skill -> {
                TypeSkills typeSkills = skill.getTypeSkills();
                return SkillVO.builder()
                .libelle(skill.getLibelle())
                        .typeSkills(typeSkills.getTitleSkill())
                        .idTypeSkills(typeSkills.getTypeId())
                .description(skill.getDescription()).build();
              })
              .collect(Collectors.toList()))
              .build();
    }
    return null;
  }

  @Override
  public List<PersonVO> findListPerson() {
    return personDAO.findAll()
        .stream()
        .map(personFinded -> PersonVO.builder()
            .mailAdresses(personFinded.getMailAdresses())
            .birthDate(personFinded.getBirthDate())
            .firstName(personFinded.getFirstName())
            .lastName(personFinded.getLastName())
            .profile(personFinded.getProfile().getProfileTitle())
                .skillVOList(personFinded.getSkills().stream()
                        .filter(skill -> skill!=null).map(skill -> {
                          TypeSkills typeSkills = skill.getTypeSkills();
                          return SkillVO.builder()
                                  .libelle(skill.getLibelle())
                                  .typeSkills(typeSkills.getTitleSkill())
                                  .idTypeSkills(typeSkills.getTypeId())
                                  .description(skill.getDescription()).build();
                        })
                        .collect(Collectors.toList()))
            .build())
        .collect(Collectors.toList());
  }

  @Override
  public List<PersonVO> findListProfilesByProfileTitle(String profileTitle) {
    return personDAO.findListProfilesByProfileTitle(profileTitle)
            .stream()
            .map(personFinded ->  PersonVO.builder()
                    .mailAdresses(personFinded.getMailAdresses())
                    .birthDate(personFinded.getBirthDate())
                    .firstName(personFinded.getFirstName())
                    .lastName(personFinded.getLastName())
                    .profile(personFinded.getProfile().getProfileTitle())
                    .skillVOList(personFinded.getSkills().stream()
                            .map(skill -> {
                              TypeSkills typeSkills = skill.getTypeSkills();
                              return SkillVO.builder()
                                      .libelle(skill.getLibelle())
                                      .typeSkills(typeSkills.getTitleSkill())
                                      .idTypeSkills(typeSkills.getTypeId())
                                      .description(skill.getDescription()).build();
                            })
                            .collect(Collectors.toList()))
                    .build())
            .collect(Collectors.toList());
  }
}
