package com.attornatus.attornatus_assessment.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import com.attornatus.attornatus_assessment.controllers.PersonAddressController;
import com.attornatus.attornatus_assessment.data.vo.v1.AddressVo;
import com.attornatus.attornatus_assessment.exceptions.BadRequestException;
import com.attornatus.attornatus_assessment.mapper.AddressMapper;
import com.attornatus.attornatus_assessment.repositories.AddressRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AddressService {
	
	@Autowired
	AddressRepository repository;

	@Autowired
	PagedResourcesAssembler<AddressVo> assembler;
	
	public AddressVo findById(Long id){
		log.info("Find for a Address");
		return AddressMapper.entityToVo(repository.findById(id)
				.orElseThrow(() -> new NotFoundException("The address isn't found")));
	}

	public AddressVo findByMainAddress(Long id){
		log.info("Find a main address");
		AddressVo address = new AddressVo();
		try { 
			address = AddressMapper.entityToVo(
					repository.findMainAddress(id));
		} catch (NotFoundException nfe) {
			nfe = new NotFoundException("The address isn't found");
		} catch (BadRequestException bre) {
			bre = new BadRequestException("Something wrong happened with your request");
		}
		 return address;
	}
	
	public PagedModel<EntityModel<AddressVo>> findAll(Integer page){
		log.info("Find Addresses");
		List<AddressVo> addresses = new ArrayList<>();
		Pageable pageable = PageRequest.of(page, 2, Sort.by("number").ascending());
		try {
			addresses = repository.findAll(pageable)
				.stream()
				.map(AddressMapper::entityToVoWithHateoas)
				.collect(Collectors.toList());
		} catch (BadRequestException bre) {
			bre = new BadRequestException("Something wrong happened with your request");
		}
		
		Page<AddressVo> pageAddress = new PageImpl<>(addresses, pageable, addresses.size());
		Link link = linkTo(methodOn(PersonAddressController.class).findAllAddress(page)).withSelfRel()
				.withType("GET-ALL").withName("Find All Addresses");
				
		return assembler.toModel(pageAddress, link);
	}
	
	public AddressVo save(AddressVo addressVo) {
		log.info("Insert a Address");
		AddressVo address = new AddressVo();
		try {
			address = AddressMapper.entityToVo(
					repository.save(AddressMapper.voToEntity(addressVo)));
		} catch (BadRequestException bre) {
			bre = new BadRequestException("Something wrong happened with your request");
		}
		return address;
	}

	public void update(AddressVo address) {
		log.info("Update a Address");
		try {
			repository.update(address.getCep(), address.getStreet(), 
					address.getNumber(), address.getCity(), address.getMainAddress(), 
					address.getKey());
		} catch (BadRequestException bre) {
			bre = new BadRequestException("Something wrong happened with your request");
		}
	}

	public void disableMainAddress(Long id) {
		log.info("Enable or Disable Main Address");
		try {
			repository.mainAddressDisable(id);
		}catch (BadRequestException bre) {
			bre = new BadRequestException("Something wrong happened with your request");
		}
	}
	
	public void enableMainAddress(Long id) {
		log.info("Enable or Disable Main Address");
		try {
			repository.mainAddressEnable(id);
		}catch (BadRequestException bre) {
			bre = new BadRequestException("Something wrong happened with your request");
		}
	}
	
	public void delete(Long id) {
		log.info("Delete a Address");
		try {
			repository.delete(AddressMapper.voToEntity(findById(id)));
		} catch (BadRequestException bre) {
			bre = new BadRequestException("Something wrong happened with your request");
		}
	}
	
}
