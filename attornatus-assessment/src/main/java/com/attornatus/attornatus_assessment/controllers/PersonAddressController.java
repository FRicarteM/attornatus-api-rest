package com.attornatus.attornatus_assessment.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.attornatus.attornatus_assessment.data.vo.v1.AddressVo;
import com.attornatus.attornatus_assessment.data.vo.v1.PersonVo;
import com.attornatus.attornatus_assessment.services.AddressService;
import com.attornatus.attornatus_assessment.services.PersonService;

@RestController
@RequestMapping("/api/person-address/v1")
public class PersonAddressController {

	@Autowired
	PersonService personService;
	
	@Autowired
	AddressService addressService;
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/create")
	public void create(@RequestBody PersonVo personVo) {
		personService.save(personVo);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/insert-new-address/{cpf}")
	public void insertNewAddress(@PathVariable(value = "cpf") String cpf, @RequestBody AddressVo addressVo) {
		PersonVo person = personService.findByCpf(cpf);
		person.getAddresses().add(addressService.save(addressVo));
		personService.save(person);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/find/{cpf}")
	public PersonVo findByCpf(@PathVariable(value = "cpf") String cpf) {
			return personService.findByCpf(cpf);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/find-person")
	public List<PersonVo> findAllPerson() {
		return personService.findAll();
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/find-address")
	public List<AddressVo> findAllAddress() {
		return addressService.findAll();
	}
	
	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping("/delete-person/{cpf}")
	public void deletePerson(@PathVariable(value = "cpf") String cpf) {
		personService.delete(cpf);
	}

	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping("/delete-address/{id}")
	public void deleteAddress(@PathVariable(value = "id") Long id) {
		addressService.delete(id);
	}
}
