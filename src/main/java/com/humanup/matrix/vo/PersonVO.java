package com.humanup.matrix.vo;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@FieldDefaults(level= AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString(of= {"firstName","lastName","mailAdresses","birthDate","profile","skillVOList"})
public class PersonVO implements Serializable {
     String firstName;
     String lastName;
     String mailAdresses;
     Date birthDate;
     String profile;
     List<SkillVO> skillVOList;
}
