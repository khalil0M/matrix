package com.humanup.matrix.bs;

import com.humanup.matrix.exceptions.ProfileException;
import com.humanup.matrix.vo.PersonVO;
import java.util.List;

public interface PersonBS {
  boolean createPerson(PersonVO person) throws ProfileException;

  boolean addSkillsPerson(List<Integer> skills, String email) throws ProfileException;

  PersonVO findPersonByMailAdresses(String mailAdresses);

  List<PersonVO> findListPerson();

  List<PersonVO> findListProfilesByProfileTitle(String profileTitle);
}
