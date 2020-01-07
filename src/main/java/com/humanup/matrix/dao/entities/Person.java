package com.humanup.matrix.dao.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@FieldDefaults(level= AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString(of= {"id","firstName","lastName","mailAdresses","birthDate","skills"})
@Entity
@Table(name = "person")
public class Person implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", updatable = false, nullable = false)
  Long id;
  @Column(name="first_name")
  String firstName;
  @Column(name="last_name")
  String lastName;
  @Column(name="mail_adresses")
  String mailAdresses;
  @Column(name="birth_date")
  Date birthDate;

  @ManyToOne
  @JoinColumn(name = "profile_id")
   Profile profile;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "person_skill",
            joinColumns = { @JoinColumn(name = "person_id") },
            inverseJoinColumns = { @JoinColumn(name = "skill_id") })
     Set<Skill> skills = new HashSet<>();
}