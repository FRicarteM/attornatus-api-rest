package com.attornatus.attornatus_assessment.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

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
	
	public List<AddressVo> findAll(){
		log.info("Find Addresses");
		List<AddressVo> addresses = new ArrayList<>();
		try {
			addresses = repository.findAll()
				.stream()
				.map(AddressMapper::entityToVo)
				.collect(Collectors.toList());
		} catch (BadRequestException bre) {
			bre = new BadRequestException("Something wrong happened with your request");
		}
		return addresses;
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
//		try {
//			if(address.getMainAddress() == true) {
//				repository.mainAddressDisable(address.getKey());
//			} if(address.getMainAddress() != true ) {
//				repository.mainAddressEnable(address.getKey());
//			}
//		} catch (BadRequestException bre) {
//			bre = new BadRequestException("Something wrong happened with your request");
//		}
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
