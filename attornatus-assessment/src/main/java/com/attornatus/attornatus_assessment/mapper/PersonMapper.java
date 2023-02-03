package com.attornatus.attornatus_assessment.mapper;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.attornatus.attornatus_assessment.controllers.PersonAddressController;
import com.attornatus.attornatus_assessment.data.vo.v1.PersonVo;
import com.attornatus.attornatus_assessment.model.Person;

public class PersonMapper {

	public static Person voToEntity(PersonVo personVo) {
		
		Person person = Person.builder()
    			.id(personVo.getKey())
    			.name(personVo.getName())
    			.cpf(personVo.getCpf())
    			.dateBirth(LocalDate.parse(personVo.getDateBirth()))
    			.addresses(AddressMapper.voToEntityList(personVo.getAddresses()))
        		.build();	
				return person;
	}

	public static PersonVo entityToVo(Person person) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		PersonVo personVo = PersonVo.builder()
    			.key(person.getId())
    			.name(person.getName())
    			.cpf(person.getCpf())
    			.dateBirth(person.getDateBirth().format(formatter))
    			.addresses(AddressMapper.entityToVoList(person.getAddresses()))
        		.build();
		return personVo;
	}

	public static PersonVo entityToVoWithHateoas(Person person) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		PersonVo personVo = PersonVo.builder()
				.key(person.getId())
				.name(person.getName())
				.cpf(person.getCpf())
				.dateBirth(person.getDateBirth().format(formatter))
				.addresses(AddressMapper.entityToVoList(person.getAddresses()))
				.build();
		
		personVo.add(linkTo(methodOn(PersonAddressController.class).findByCpf(personVo.getCpf())).withRel("Get a Person by CPF")
				.withType("GET-BY").withName("Find By CPF"));
		personVo.add(linkTo(methodOn(PersonAddressController.class).findMainAddressByPerson(personVo.getCpf()))
				.withRel("Get Main Address").withType("GET-BY").withName("Find the Main Address of a Person"));
		personVo.add(linkTo(methodOn(PersonAddressController.class).findAllAddress()).withRel("Get all Address")
				.withType("GET-ALL").withName("Find All Addresses"));
		
		return personVo;
	}
	
}
