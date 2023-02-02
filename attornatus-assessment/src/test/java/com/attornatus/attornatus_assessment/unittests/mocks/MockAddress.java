package com.attornatus.attornatus_assessment.unittests.mocks;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.attornatus.attornatus_assessment.data.vo.v1.AddressVo;
import com.attornatus.attornatus_assessment.model.Address;

public class MockAddress {

	public Address mockEntity() {
		return mockEntity(0);
	}

	public AddressVo mockVo() {
		return mockVo(0);
	}

	public List<Address> mockEntityList() {
		List<Address> addresses = IntStream.range(0, 10)
				.mapToObj(i -> mockEntity(i))
				.collect(Collectors.toList()); 
		return addresses;
	}

	public List<AddressVo> mockVoList() {
		List<AddressVo> addresses = IntStream.range(0, 10)
				.mapToObj(i -> mockVo(i))
				.collect(Collectors.toList()); 
		return addresses;
	}

	public Address mockEntity(Integer number) {
		Address address = Address.builder()
				.id(number.longValue())
				.cep("60823-11" + number)
				.street("rua" + number)
				.number(number)
				.city("cidade" + number)
				.mainAddress(true)
				.build();
		return address;
	}

	public AddressVo mockVo(Integer number) {
		AddressVo address = AddressVo.builder()
				.key( number.longValue())
				.cep("60823-11" + number)
				.street("rua" + number)
				.number(number)
				.city("cidade" + number)
				.mainAddress(true)
				.build();
		return address;
	}	
}
