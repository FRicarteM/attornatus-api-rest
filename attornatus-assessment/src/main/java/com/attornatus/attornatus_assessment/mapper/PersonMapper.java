package com.attornatus.attornatus_assessment.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
	
}
