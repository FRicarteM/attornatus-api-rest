package com.attornatus.attornatus_assessment.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import com.attornatus.attornatus_assessment.data.vo.v1.AddressVo;
import com.attornatus.attornatus_assessment.mapper.AddressMapper;
import com.attornatus.attornatus_assessment.model.Address;
import com.attornatus.attornatus_assessment.repositories.AddressRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AddressService {
	
	@Autowired
	AddressRepository repository;
	
	public AddressVo findById(Long id){
		log.info("Find for a Address");
		return AddressMapper.entityToVo(repository.findById(id)
				.orElseThrow(() -> new NotFoundException("")));
	}

	public AddressVo findByMainAddress(Long id){
		log.info("Find a main address");
		return AddressMapper.entityToVo(
				repository.findMainAddress(id));
	}
	
	public List<AddressVo> findAll(){
		log.info("Find Addresses");
		return repository.findAll()
				.stream()
				.map(AddressMapper::entityToVo)
				.collect(Collectors.toList());
	}
	
	public AddressVo save(AddressVo addressVo) {
		log.info("Insert a Address");
		Address address = repository.save(AddressMapper.voToEntity(addressVo));
		return AddressMapper.entityToVo(address);
	}

	public void update(AddressVo address) {
		log.info("Update a Address");
		repository.update(address.getCep(), address.getStreet(), 
				address.getNumber(), address.getCity(), address.getMainAddress(), 
				address.getKey());
	}
	
	public void delete(Long id) {
		log.info("Delete a Address");
		repository.delete(AddressMapper.voToEntity(findById(id)));
	}
	
}
