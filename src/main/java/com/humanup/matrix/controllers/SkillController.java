package com.humanup.matrix.controllers;

import com.humanup.matrix.bs.SkillBS;
import com.humanup.matrix.vo.SkillVO;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SkillController {
  @Autowired private SkillBS skillBS;

  @Operation(
      summary = "Create Skill",
      description = " Create new Skill by Libelle, Description ...",
      tags = {"skill"})
  @RequestMapping(
      value = "/skill",
      method = RequestMethod.POST,
      consumes = {"application/json"})
  @ResponseBody
  public ResponseEntity createSkill(@RequestBody SkillVO skill) {
    Optional<Object> findskill =
        Optional.ofNullable(skillBS.findSkillByLibelle(skill.getLibelle()));

    if (findskill.isPresent()) {
      return ResponseEntity.status(HttpStatus.FOUND).body("This Skill is Founded");
    }
    skillBS.createSkill(skill);
    return ResponseEntity.status(HttpStatus.CREATED).body(skill);
  }

  @Operation(
      summary = "Find skill by libelle",
      description = "Skill search by %libelle% format",
      tags = {"skill"})
  @RequestMapping(value = "/skill", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity getSkillInfo(
      @RequestParam(value = "libelle", defaultValue = "C++") String libelle) {
    Optional<SkillVO> findSkill = Optional.ofNullable(skillBS.findSkillByLibelle(libelle));
    if (findSkill.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    return ResponseEntity.status(HttpStatus.OK).body(findSkill.get());
  }

  @Operation(
      summary = "Find all skill",
      description = "Find all skills",
      tags = {"skill"})
  @RequestMapping(value = "/skill/all", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity getAllSkillInfo() {
    List<SkillVO> findskills = skillBS.findListSkill();

    if (findskills.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    return ResponseEntity.status(HttpStatus.OK).body(findskills);
  }

  @Operation(
      summary = "Find all skill by type",
      description = "Find all skill by type title",
      tags = {"skill"})
  @RequestMapping(value = "/skill/all/type", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity getTypeSkills(
      @RequestParam(value = "typeSkills", defaultValue = "audace") String typeSkills) {
    Optional<List<SkillVO>> findSkill =
        Optional.ofNullable(skillBS.findListSkillByTypeTitle(typeSkills));
    if (findSkill.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("This Type not Found");
    }
    return ResponseEntity.status(HttpStatus.OK).body(findSkill.get());
  }
}
