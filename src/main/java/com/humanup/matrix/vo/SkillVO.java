package com.humanup.matrix.vo;

import java.io.Serializable;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString(of = {"idTypeSkills", "libelle", "description", "typeSkills"})
public class SkillVO implements Serializable {
  Long idTypeSkills;
  String libelle;
  String description;
  String typeSkills;
}
