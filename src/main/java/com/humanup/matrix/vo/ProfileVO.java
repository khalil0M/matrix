package com.humanup.matrix.vo;

import java.io.Serializable;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString(of = {"profileTitle", "profileDescription", "countPerson"})
public class ProfileVO implements Serializable {
  String profileTitle;
  String profileDescription;
  int countPerson;
}
