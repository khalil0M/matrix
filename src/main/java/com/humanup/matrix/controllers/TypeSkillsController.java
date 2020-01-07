package com.humanup.matrix.controllers;

import com.humanup.matrix.bs.ProfileBS;
import com.humanup.matrix.bs.TypeSkillsBS;
import com.humanup.matrix.vo.TypeSkillsVO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TypeSkillsController {
    @Autowired
    private TypeSkillsBS typeSkillsBS;


    @Operation(summary = "Create Type Skills", description = " Create new type skills by title ...", tags = { "skills" })
    @RequestMapping(value="/typeskills", method= RequestMethod.POST,consumes={ "application/json"})
    @ResponseBody
    public ResponseEntity createTypeSkills(@RequestBody TypeSkillsVO typeSkills){
        Optional<Object> findTypeSkills = Optional.ofNullable(typeSkillsBS.findByTypeSkillsTitle(typeSkills.getTitleSkill()));

        if(findTypeSkills.isPresent()){
            return ResponseEntity.status(HttpStatus.FOUND).body("This type is Founded");
        }
        typeSkillsBS.createTypeSkills(typeSkills);
        return ResponseEntity.status(HttpStatus.CREATED).body(typeSkillsBS);
    }

    @Operation(summary = "Find types kills by title", description = "Type Skills search by %title% format", tags = { "typeskills" })
    @RequestMapping(value="/typeskills/title", method=RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getTypeInfo(@RequestParam(value="title", defaultValue="Spring Boot") String title){
        Optional<TypeSkillsVO> findTypeSkillsTitle = Optional.ofNullable(typeSkillsBS.findByTypeSkillsTitle(title));
        if(findTypeSkillsTitle.isEmpty()){
            Optional<List<TypeSkillsVO>> findType = Optional.ofNullable(typeSkillsBS.findListTypeSkillsByTitle(title));
            if(findType.get().isEmpty())
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("This type is not Found");
            }
            return ResponseEntity.status(HttpStatus.OK).body(findType.get());
        }
        return ResponseEntity.status(HttpStatus.OK).body(findTypeSkillsTitle.get());
    }

    @Operation(summary = "Find types kills by id", description = "Type Skills search by %id% format", tags = { "typeskills" })
    @RequestMapping(value="/typeskills/id", method=RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getTypeInfoById(@RequestParam(value="id", defaultValue="1") Long id){
        Optional<TypeSkillsVO> findType = Optional.ofNullable(typeSkillsBS.findByTypeSkillsByID(id));
        if(findType.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("This type is not Found");
            }
        return ResponseEntity.status(HttpStatus.OK).body(findType.get());
    }
    @Operation(summary = "Find all type skills", description = "Find all  type skill", tags = { "typeskills" })
    @RequestMapping(value="/typeskills/all", method=RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getAlltypeInfo(){
        List<TypeSkillsVO> findType = typeSkillsBS.findListTypeSkills();

        if(findType.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(findType);
    }

}
