package com.attornatus.attornatus_assessment.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import com.attornatus.attornatus_assessment.data.vo.v1.AddressVo;
import com.attornatus.attornatus_assessment.data.vo.v1.PersonVo;
import com.attornatus.attornatus_assessment.mapper.AddressMapper;
import com.attornatus.attornatus_assessment.mapper.PersonMapper;
import com.attornatus.attornatus_assessment.model.Address;
import com.attornatus.attornatus_assessment.model.Person;
import com.attornatus.attornatus_assessment.repositories.AddressRepository;
import com.attornatus.attornatus_assessment.repositories.PersonRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PersonService {

	@Autowired
	PersonRepository repository;
	
	@Autowired
	AddressRepository addressRepository;
	
	public PersonVo findById(Long id){
		log.info("Find for a Person");
		PersonVo personVo = PersonMapper.entityToVo(
				repository.findById(id).orElseThrow(() -> new NotFoundException("")));
		 return personVo;
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
			Address address = addressRepository.save(AddressMapper.voToEntity(vo));
			person.getAddresses().add(address);
		}
		repository.save(person);
	}
	
	public void delete(Long id) {
		log.info("Delete a Person");
		Person person = PersonMapper.voToEntity(findById(id));
		repository.delete(person);
	}
	
}
