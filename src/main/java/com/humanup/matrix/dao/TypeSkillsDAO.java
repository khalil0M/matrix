package com.humanup.matrix.dao;

import com.humanup.matrix.dao.entities.TypeSkills;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeSkillsDAO extends JpaRepository<TypeSkills, Long> {
  TypeSkills findByTitleSkill(String titleSkill);

  List<TypeSkills> findAll();

  TypeSkills findByTypeId(long typeId);

  @Query("SELECT t FROM TypeSkills t WHERE lower(t.titleSkill) like %:titleSkill% ")
  List<TypeSkills> findTypeSkillsByTitle(String titleSkill);
}
