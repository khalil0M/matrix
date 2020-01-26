package com.humanup.matrix.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@FieldDefaults(level= AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString(of= {"idTypeSkills","libelle","description","typeSkills"})
public class SkillVO implements Serializable {
	@JsonIgnore
	 Long idTypeSkills;
	 String libelle;
	 String description;
	 String typeSkills;
}
