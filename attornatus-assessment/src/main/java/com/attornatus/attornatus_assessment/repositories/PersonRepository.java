package com.attornatus.attornatus_assessment.repositories;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.attornatus.attornatus_assessment.model.Person;

import jakarta.transaction.Transactional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

	Person findByCpf(String cpf);
	
	@Modifying
	@Transactional
	@Query("UPDATE Person p SET p.name = :name, p.cpf = :cpf, p.dateBirth = :dateBirth WHERE p.id = :id")
	void update(@Param("name") String name,@Param("cpf") String cpf, 
			@Param("dateBirth") LocalDate dateBirth, @Param("id") Long id);
	
}
