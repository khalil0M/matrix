package com.humanup.matrix.dao;

import com.humanup.matrix.dao.entities.Person;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonDAO extends JpaRepository<Person, Long> {
  Person findByMailAdresses(String mailAdresses);

  List<Person> findAll();

  Person findById(long id);

  @Query("SELECT p FROM Person p WHERE lower(p.profile.profileTitle) like %:profileTitle% ")
  List<Person> findListProfilesByProfileTitle(String profileTitle);
}
