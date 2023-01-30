package com.attornatus.attornatus_assessment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.attornatus.attornatus_assessment.model.Address;

import jakarta.transaction.Transactional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

	@Query("SELECT a FROM Address a WHERE a.mainAddress = TRUE AND a.id = :id")
	Address findMainAddress(@Param("id") Long id);
	
	@Modifying
	@Transactional
	@Query("UPDATE Address a SET a.cep = :cep, a.street = :street, "
			+ "a.number = :number, a.city = :city, a.mainAddress = :mainAddress WHERE a.id = :id")
	void update(@Param("cep") String cep, @Param("street") String street,
    		@Param("number") int number, @Param("city") String city,
    		@Param("mainAddress") Boolean mainAddress, @Param("id") Long id);
	
	@Modifying
	@Transactional
	@Query("UPDATE Address a SET a.mainAddress = true WHERE a.id = :id")
	void mainAddressEnable(@Param("id") Long id);
	
	@Modifying
	@Transactional
	@Query("UPDATE Address a SET a.mainAddress = false WHERE a.id = :id")
	void mainAddressDisable(@Param("id") Long id);
	
}
