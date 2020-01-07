package com.humanup.matrix.vo;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level= AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString(of= {"idTypeSkills","libelle","description","typeSkills"})
public class SkillVO {
	 Long idTypeSkills;
	 String libelle;
	 String description;
	 String typeSkills;
}
