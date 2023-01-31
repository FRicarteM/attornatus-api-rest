package com.attornatus.attornatus_assessment.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;

import com.attornatus.attornatus_assessment.controllers.PersonAddressController;
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
		
		person.add(linkTo(methodOn(PersonAddressController.class).findByCpf(cpf)).withSelfRel()
				.withType("GET-BY").withName("Find By CPF"));
		person.add(linkTo(methodOn(PersonAddressController.class).findMainAddressByPerson(cpf))
				.withRel("Get Main Address").withType("GET-BY").withName("Find the Main Address of a Person"));
		person.add(linkTo(methodOn(PersonAddressController.class).findAllPerson()).withRel("Get all People")
				.withType("GET-ALL").withName("Find All People"));
		person.add(linkTo(methodOn(PersonAddressController.class).findAllAddress()).withRel("Get all Address")
				.withType("GET-ALL").withName("Find Akk Addresses"));
		
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
		
		mainAddress.add(linkTo(methodOn(PersonAddressController.class).findMainAddressByPerson(cpf)).withSelfRel()
				.withType("GET-BY").withName("Find the Main Address of a Person"));
		mainAddress.add(linkTo(methodOn(PersonAddressController.class).findByCpf(cpf)).withRel("Get a Person by CPF")
				.withType("GET-BY").withName("Find By CPF"));
		mainAddress.add(linkTo(methodOn(PersonAddressController.class).findAllPerson()).withRel("Get all People")
				.withType("GET-ALL").withName("Find All People"));
		mainAddress.add(linkTo(methodOn(PersonAddressController.class).findAllAddress()).withRel("Get all Address")
				.withType("GET-ALL").withName("Find Akk Addresses"));
		
		return mainAddress;
	}
	
	public CollectionModel<PersonVo> findAll() {
		log.info("Find People");
		CollectionModel<PersonVo> people = null; 
		try {
			people = CollectionModel.of((repository.findAll()
				.stream()
				.map(PersonMapper::entityToVoWithHateoas)
				.collect(Collectors.toList())));
		} catch (BadRequestException bre) {
			bre = new BadRequestException("Something wrong happened with your request");
		}		
		
		people.add(linkTo(methodOn(PersonAddressController.class).findAllPerson()).withSelfRel()
				.withType("GET-ALL").withName("Find All People"));
		
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
