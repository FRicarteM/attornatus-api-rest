package com.attornatus.attornatus_assessment.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.attornatus.attornatus_assessment.data.vo.v1.AddressVo;
import com.attornatus.attornatus_assessment.data.vo.v1.PersonVo;
import com.attornatus.attornatus_assessment.services.AddressService;
import com.attornatus.attornatus_assessment.services.PersonService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/person-address/v1")
public class PersonAddressController {

	@Autowired
	PersonService personService;
	
	@Autowired
	AddressService addressService;
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/create")
	public void create(@RequestBody @Valid PersonVo personVo) {
		personService.save(personVo);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/insert-new-address/{cpf}")
	public void insertNewAddress(@PathVariable(value = "cpf") String cpf, @RequestBody @Valid AddressVo addressVo) {
		personService.insertNewAddress(cpf, addressVo);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@PutMapping("/update-person")
	public void update (@RequestBody @Valid PersonVo personVo) {
		personService.updatePerson(personVo);
	}

	@ResponseStatus(HttpStatus.OK)
	@PatchMapping("/update-person-address")
	public void updatePersonAddress (@RequestBody @Valid PersonVo personVo) {
			personService.update(personVo);
	}

	@ResponseStatus(HttpStatus.OK)
	@PutMapping("/update-address")
	public void updateAddress (@RequestBody @Valid AddressVo addressVo) {
		addressService.update(addressVo);
	}

	@ResponseStatus(HttpStatus.OK)
	@PutMapping("/update-main-address/{cpf}/{id}")
	public void updateMainAddress(@PathVariable(value = "cpf") String cpf, @PathVariable(value = "id") Long id) {
		personService.enableOrDisableMainAddressByPerson(cpf, id);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/find/{cpf}")
	public PersonVo findByCpf(@PathVariable(value = "cpf") String cpf) {
			return personService.findByCpf(cpf);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/find-main-address/{cpf}")
	public AddressVo findMainAddressByPerson(@PathVariable(value = "cpf") String cpf) {
			return personService.findMainAddressByPerson(cpf);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/find-person")
	public CollectionModel<PersonVo> findAllPerson() {
		return personService.findAll();
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/find-address")
	public CollectionModel<AddressVo> findAllAddress() {
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
