package com.humanup.matrix.controllers;

import com.humanup.matrix.aop.dto.PersonException;
import com.humanup.matrix.aop.dto.SkillException;
import com.humanup.matrix.bs.PersonBS;
import com.humanup.matrix.vo.PersonVO;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PersonController {
  @Autowired private PersonBS personBS;

  @Operation(
      summary = "Create Person",
      description = " Create new person by firstname, lastname ...",
      tags = {"person"})
  @RequestMapping(
      value = "/person",
      method = RequestMethod.POST,
      consumes = {"application/json"})
  @ResponseBody
  public ResponseEntity createPerson(@RequestBody PersonVO person) throws PersonException {
    Optional<Object> findPerson =
        Optional.ofNullable(personBS.findPersonByMailAdresses(person.getMailAdresses()));

    if (findPerson.isPresent()) {
      return ResponseEntity.status(HttpStatus.FOUND).body("This User is Founded");
    }
    personBS.createPerson(person);
    return ResponseEntity.status(HttpStatus.CREATED).body(person);
  }

  @Operation(
      summary = "Create Skills",
      description = " Create new kills by email",
      tags = {"person"})
  @RequestMapping(
      value = "/person/skills",
      method = RequestMethod.POST,
      consumes = {"application/json"})
  @ResponseBody
  public ResponseEntity addPersonSkills(
      @RequestParam(value = "email", defaultValue = "robot@sqli.com") String email,
      @RequestBody List<Integer> skills)
      throws PersonException, SkillException {
    Optional<Object> findPerson = Optional.ofNullable(personBS.addSkillsPerson(skills, email));
    if (findPerson.isEmpty()) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Forbidden Skills Information");
    }
    return ResponseEntity.status(HttpStatus.CREATED).body(findPerson.get());
  }

  @Operation(
      summary = "Find person by email",
      description = "Person search by %email% format",
      tags = {"person"})
  @RequestMapping(value = "/person", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity getPersonInfo(
      @RequestParam(value = "email", defaultValue = "robot@sqli.com") String email) {
    Optional<PersonVO> findPerson = Optional.ofNullable(personBS.findPersonByMailAdresses(email));
    if (findPerson.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    return ResponseEntity.status(HttpStatus.OK).body(findPerson.get());
  }

  @Operation(
      summary = "Find all persosn",
      description = "Find all persons",
      tags = {"person"})
  @RequestMapping(value = "/person/all", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity getAllPersonInfo() {
    List<PersonVO> findPersons = personBS.findListPerson();

    if (findPersons.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    return ResponseEntity.status(HttpStatus.OK).body(findPersons);
  }

  @Operation(
      summary = "Find all persons by profile",
      description = "Find all persons by profile title",
      tags = {"person"})
  @RequestMapping(value = "/person/all/profile", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity getProfilePersons(
      @RequestParam(value = "title", defaultValue = "Spring Developer") String profileTitle) {
    Optional<List<PersonVO>> findProfile =
        Optional.ofNullable(personBS.findListProfilesByProfileTitle(profileTitle));
    if (findProfile.get().isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("This Title not Found");
    }
    return ResponseEntity.status(HttpStatus.OK).body(findProfile.get());
  }
}
