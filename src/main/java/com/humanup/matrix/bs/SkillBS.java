package com.humanup.matrix.bs;

import com.humanup.matrix.aop.dto.SkillException;
import com.humanup.matrix.vo.SkillVO;
import java.util.List;

public interface SkillBS {
  boolean createSkill(SkillVO Skill) throws SkillException;

  SkillVO findSkillByLibelle(String libelle);

  List<SkillVO> findListSkill();

  List<SkillVO> findListSkillByTypeTitle(String titleSkill);
}
