package com.humanup.matrix.dao;

import com.humanup.matrix.dao.entities.Skill;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillDAO extends JpaRepository<Skill, Long> {
  Skill findSkillByLibelle(String libelle);

  List<Skill> findAll();

  Skill findById(long id);

  @Query("SELECT s FROM Skill s WHERE lower(s.typeSkills.titleSkill) like %:titleSkill% ")
  List<Skill> findListSkillByTypeTitle(String titleSkill);
}
