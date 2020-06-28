package com.humanup.matrix.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString(of = {"firstName", "lastName", "mailAdresses", "birthDate", "profile", "skillVOList"})
public class PersonVO implements Serializable {
  String firstName;
  String lastName;
  String mailAdresses;
  Date birthDate;
  String profile;
  List<SkillVO> skillVOList;
}
