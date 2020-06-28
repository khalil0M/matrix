package com.humanup.matrix.vo;

import java.io.Serializable;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString(of = {"titleSkill"})
public class TypeSkillsVO implements Serializable {
  String titleSkill;
}
