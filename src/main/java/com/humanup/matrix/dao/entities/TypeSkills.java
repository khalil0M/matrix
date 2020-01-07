package com.humanup.matrix.dao.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@FieldDefaults(level= AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString(of= {"typeId","titleSkill","skillList"})
@Entity
@Table(name = "type_skills")
public class TypeSkills implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="type_id")
	 Long typeId;
	@Column(name="title_skill")
	 String titleSkill;

	@OneToMany(mappedBy="typeSkills",fetch=FetchType.EAGER)
	 List<Skill> skillList;
}
