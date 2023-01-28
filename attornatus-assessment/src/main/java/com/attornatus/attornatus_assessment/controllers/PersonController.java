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

import com.attornatus.attornatus_assessment.data.vo.v1.PersonVo;
import com.attornatus.attornatus_assessment.services.PersonService;

@RestController
@RequestMapping("/api/person/v1")
public class PersonController {

	@Autowired
	PersonService service;
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public void create(@RequestBody PersonVo personVo) {
			service.save(personVo);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/find/{id}")
	public PersonVo findById(@PathVariable(value = "id") Long id) {
			return service.findById(id);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/find")
	public List<PersonVo> findAllPerson() {
		return service.findAll();
	}
	
	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable(value = "id") Long id) {
		service.delete(id);
	}
}
