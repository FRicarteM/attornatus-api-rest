package com.attornatus.attornatus_assessment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.attornatus.attornatus_assessment.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {


	
}
