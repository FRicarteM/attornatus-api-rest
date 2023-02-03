package com.attornatus.attornatus_assessment.mapper;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import com.attornatus.attornatus_assessment.controllers.PersonAddressController;
import com.attornatus.attornatus_assessment.data.vo.v1.AddressVo;
import com.attornatus.attornatus_assessment.model.Address;

public class AddressMapper {

	public static Address voToEntity(AddressVo addressVo) {
		Address address = Address.builder()
    			.id(addressVo.getKey())
    			.cep(addressVo.getCep())
        		.street(addressVo.getStreet())
        		.number(addressVo.getNumber())
        		.city(addressVo.getCity())
        		.mainAddress(addressVo.getMainAddress())
        		.build();
		return address;
	}
	
	public static List<Address> voToEntityList(List<AddressVo> addresses) {
		List<Address> addressList = addresses.stream()
				.map(AddressMapper::voToEntity)
				.collect(Collectors.toList());	
		return addressList;
	}
	
	public static AddressVo entityToVo(Address address) {
		
		AddressVo addressVo = AddressVo.builder()
    			.key(address.getId())
    			.cep(address.getCep())
        		.street(address.getStreet())
        		.number(address.getNumber())
        		.city(address.getCity())
        		.mainAddress(address.getMainAddress())
        		.build();		
		return addressVo;
	}

	public static AddressVo entityToVoWithHateoas(Address address) {
		
		AddressVo addressVo = AddressVo.builder()
				.key(address.getId())
				.cep(address.getCep())
				.street(address.getStreet())
				.number(address.getNumber())
				.city(address.getCity())
				.mainAddress(address.getMainAddress())
				.build();	
		
		addressVo.add(linkTo(methodOn(PersonAddressController.class).findAllPerson()).withRel("Get all People")
				.withType("GET-ALL").withName("Find All People"));
		
		return addressVo;
	}
	
	public static List<AddressVo> entityToVoList(List<Address> address) {
		List<AddressVo> addressVoList = address.stream()
				.map(AddressMapper::entityToVo)
				.collect(Collectors.toList());		
		return addressVoList;
	}
	
}
