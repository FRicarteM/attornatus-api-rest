package com.attornatus.attornatus_assessment.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.attornatus.attornatus_assessment.data.vo.v1.AddressVo;
import com.attornatus.attornatus_assessment.data.vo.v1.PersonVo;
import com.attornatus.attornatus_assessment.exceptions.BadRequestException;
import com.attornatus.attornatus_assessment.exceptions.NotFoundException;
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
	
	public PersonVo findById(Long id) {
		log.info("Find for a Person");
		return PersonMapper.entityToVo(repository.findById(id)
				.orElseThrow(() -> new NotFoundException("The person isn't found")));
	}

	public PersonVo findByCpf(String cpf) {
		log.info("Find for a Person by your CPF");
		PersonVo person = new PersonVo();
		try {
			person = PersonMapper.entityToVo(repository.findByCpf(cpf));
		} catch (NotFoundException nfe) {
			nfe = new NotFoundException("The person isn't found");
		} catch (BadRequestException bre) {
			bre = new BadRequestException("Something wrong happened with your request");
		}
		return person;
	}
	
	public AddressVo findMainAddressByPerson(String cpf) {
		log.info("Find a main address for a person");
		PersonVo person = findByCpf(cpf);
		List<AddressVo> addresses = person.getAddresses();
		AddressVo mainAddress = new AddressVo();
		try {
		for(AddressVo address: addresses) {
			if(address.getMainAddress() == true)
				mainAddress = service.findByMainAddress(address.getKey());	
			}
		} catch (NotFoundException nfe) {
			nfe = new NotFoundException("The person isn't found");
		}
		
		return mainAddress;
	}
	
	public List<PersonVo> findAll() {
		log.info("Find People");
		List<PersonVo> people = new ArrayList<>(); 
		try {
			people = repository.findAll()
				.stream()
				.map(PersonMapper::entityToVo)
				.collect(Collectors.toList());
		} catch (BadRequestException bre) {
			bre = new BadRequestException("Something wrong happened with your request");
		}
		return people;
	}
	
	public void save(PersonVo personVo) {
		log.info("Insert a Person");
		List<AddressVo> addresses = personVo.getAddresses();
		Person person = PersonMapper.voToEntity(personVo);
		person.getAddresses().clear();
		try {
			addresses.forEach(vo -> person.getAddresses()
					.add(AddressMapper.voToEntity(service.save(vo))));
		} catch (BadRequestException bre) {
			bre = new BadRequestException("Something wrong happened with your request");
		}
		repository.save(person);
	}
	
	public void insertNewAddress(String cpf, AddressVo addressVo) {
		PersonVo person = findByCpf(cpf);
			for(AddressVo vo : person.getAddresses()){
				if(vo.getCep().equals(addressVo.getCep()) && vo.getStreet().equals(addressVo.getStreet())
						&& vo.getNumber() == addressVo.getNumber() && vo.getCity().equals(addressVo.getCity())
						&& vo.getMainAddress().equals(addressVo.getMainAddress())) {
					throw new BadRequestException("The address have existed");
				} if(vo.getMainAddress() == true && addressVo.getMainAddress() == true) {
					throw new BadRequestException("Con only have an addres how main address");
				}	
			}
			person.getAddresses().add(service.save(addressVo));
			save(person);
	}
	
	public void updatePerson(PersonVo personVo) {
		log.info("Update a Person");
		try {
			repository.update(personVo.getName(), personVo.getCpf(), 
					LocalDate.parse(personVo.getDateBirth()), personVo.getKey());
		} catch (NotFoundException nfe) {
			nfe = new NotFoundException("The person isn't found");
		} catch (BadRequestException bre) {
			bre = new BadRequestException("Something wrong happened with your request");
		}
		
	}

	public void update(PersonVo personVo) {
		log.info("Update a Person");
		List<AddressVo> addresses = personVo.getAddresses();
		try {
			addresses.forEach(vo -> service.update(vo));
			repository.update(personVo.getName(), personVo.getCpf(), 
					LocalDate.parse(personVo.getDateBirth()), personVo.getKey());
		} catch (NotFoundException nfe) {
			nfe = new NotFoundException("The person isn't found");
		} catch (BadRequestException bre) {
			bre = new BadRequestException("Something wrong happened with your request");
		}	
	}
	
	public void enableOrDisableMainAddressByPerson(String cpf, Long id) {
		log.info("Enable or Disable Main Address");
			PersonVo person = findByCpf(cpf);
			List<AddressVo> addresses = person.getAddresses();
			AddressVo address = service.findById(id);
			if (address.getMainAddress() == true) {
				service.disableMainAddress(id);
			} else {
				Long countTrue = addresses.stream()
						.filter(vo -> vo.getMainAddress()).count();
				if(countTrue >= 1) {
					throw new BadRequestException("Con only have an addres how main address");
				} else {
					service.enableMainAddress(id);
				}
			}
	}

	public void delete(String cpf) throws BadRequestException, NotFoundException {
		log.info("Delete a Person");
		try {
		repository.delete(PersonMapper.voToEntity(findByCpf(cpf)));
		} catch (BadRequestException bre) {
			bre = new BadRequestException("Something wrong happened with your request");
		}
	}
	
}
