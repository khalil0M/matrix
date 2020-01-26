package com.humanup.matrix.vo;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@FieldDefaults(level= AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString(of= {"profileTitle","profileDescription","countPerson"})
public class ProfileVO implements Serializable {
     String profileTitle;
     String profileDescription;
     int countPerson;

}
