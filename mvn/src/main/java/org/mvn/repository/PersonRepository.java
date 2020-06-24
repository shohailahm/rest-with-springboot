package org.mvn.repository;

import org.mvn.data.model.Person;
import org.mvn.data.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Modifying
    @Query("UPDATE Person p SET p.enabled=false WHERE p.id=:id")
    void disablePerson(@Param("id") long id);

    @Query("SELECT p FROM Person p where p.name LIKE LOWER(CONCAT('%',:firstName,'%'))")
    Page<Person> findUserByName(@Param("firstName") String firstName, Pageable pageable);
}