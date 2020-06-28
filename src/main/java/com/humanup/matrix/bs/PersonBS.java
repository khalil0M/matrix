package com.humanup.matrix.bs;

import com.humanup.matrix.aop.dto.PersonException;
import com.humanup.matrix.aop.dto.SkillException;
import com.humanup.matrix.vo.PersonVO;
import java.util.List;

public interface PersonBS {
  boolean createPerson(PersonVO person) throws PersonException;

  boolean addSkillsPerson(List<Integer> skills, String email) throws SkillException;

  PersonVO findPersonByMailAdresses(String mailAdresses);

  List<PersonVO> findListPerson();

  List<PersonVO> findListProfilesByProfileTitle(String profileTitle);
}
