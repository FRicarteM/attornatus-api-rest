package com.attornatus.attornatus_assessment.services;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import com.attornatus.attornatus_assessment.data.vo.v1.AddressVo;
import com.attornatus.attornatus_assessment.data.vo.v1.PersonVo;
import com.attornatus.attornatus_assessment.mapper.AddressMapper;
import com.attornatus.attornatus_assessment.mapper.PersonMapper;
import com.attornatus.attornatus_assessment.model.Person;
import com.attornatus.attornatus_assessment.repositories.PersonRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PersonService {

	@Autowired
	PersonRepository repository;
	
	@Autowired
	AddressService service;
	
	public PersonVo findById(Long id){
		log.info("Find for a Person");
		return PersonMapper.entityToVo(repository.findById(id)
				.orElseThrow(() -> new NotFoundException("")));
	}

	public PersonVo findByCpf(String cpf){
		log.info("Find for a Person by your CPF");
		return PersonMapper.entityToVo(repository.findByCpf(cpf));
	}
	
	public List<PersonVo> findAll(){
		log.info("Find People");
		return repository.findAll()
				.stream()
				.map(PersonMapper::entityToVo)
				.collect(Collectors.toList());
	}
	
	public void save(PersonVo personVo) {
		log.info("Insert a Person");
		List<AddressVo> addresses = personVo.getAddresses();
		Person person = PersonMapper.voToEntity(personVo);
		person.getAddresses().clear();
		for(AddressVo vo: addresses) {
			person.getAddresses()
			.add(AddressMapper.voToEntity(service.save(vo)));
		}
		repository.save(person);
	}
	
	public void update(PersonVo personVo) {
		log.info("Update a Person");
		Person person = PersonMapper.voToEntity(findByCpf(personVo.getCpf()));
		person.setName(personVo.getName());
		person.setCpf(personVo.getCpf());
		person.setDateBirth(LocalDate.parse(personVo.getDateBirth()));
		List<AddressVo> addresses = personVo.getAddresses();
		for(AddressVo vo: addresses) {
			person.getAddresses()
			.add(AddressMapper.voToEntity(service.save(vo)));
		}
		repository.save(person);
		
	}

	public void delete(String cpf) {
		log.info("Delete a Person");
		repository.delete(PersonMapper.voToEntity(findByCpf(cpf)));
	}
	
}
