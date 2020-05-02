package com.humanup.matrix.bs;

import com.humanup.matrix.vo.SkillVO;
import java.util.List;

public interface SkillBS {
  boolean createSkill(SkillVO Skill);

  SkillVO findSkillByLibelle(String libelle);

  List<SkillVO> findListSkill();

  List<SkillVO> findListSkillByTypeTitle(String titleSkill);
}
