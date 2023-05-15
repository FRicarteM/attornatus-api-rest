package com.attornatus.attornatus_assessment.unittests.mockito.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.attornatus.attornatus_assessment.data.vo.v1.AddressVo;
import com.attornatus.attornatus_assessment.data.vo.v1.PersonVo;
import com.attornatus.attornatus_assessment.model.Person;
import com.attornatus.attornatus_assessment.repositories.PersonRepository;
import com.attornatus.attornatus_assessment.services.AddressService;
import com.attornatus.attornatus_assessment.services.PersonService;
import com.attornatus.attornatus_assessment.unittests.mocks.MockAddress;
import com.attornatus.attornatus_assessment.unittests.mocks.MockPerson;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

	MockPerson personInput;
	MockAddress addressInput;
	
	@InjectMocks
	private PersonService service;

	@Mock
	PersonRepository repository;

	@Mock
	private AddressService addressService;
	
	@BeforeEach
	void setUpMokcs() throws Exception {
		personInput = new MockPerson();
		addressInput = new MockAddress();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testFindById() {
		Person entity = personInput.mockEntity(1);
		
		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		PersonVo response = service.findById(1L);
		
		assertNotNull(response.getKey());
		assertNotNull(response.getName());
		assertNotNull(response.getCpf());
		assertNotNull(response.getDateBirth());
		assertNotNull(response.getAddresses());
		
	    assertEquals("Person1", response.getName());
	    assertEquals("000.000.000-01", response.getCpf());
	    assertEquals("1990-12-21", response.getDateBirth());
	    assertEquals(addressInput.mockVoList(), response.getAddresses());
	}

	@Test
	void testFindByCpf() {
		Person entity = personInput.mockEntity(1);
		
		when(repository.findByCpf(entity.getCpf())).thenReturn(entity);
		PersonVo response = service.findByCpf(entity.getCpf());
		
		assertNotNull(response.getKey());
		assertNotNull(response.getLinks());
		assertNotNull(response.getName());
		assertNotNull(response.getCpf());
		assertNotNull(response.getDateBirth());
		assertNotNull(response.getAddresses());
		assertTrue(response.getLinks().toString().contains("</api/person-address/v1/find/000.000.000-01>;rel=\"self\";type=\"GET-BY\";name=\"Find By CPF\","
				+ "</api/person-address/v1/find-main-address/000.000.000-01>;rel=\"Get Main Address\";type=\"GET-BY\";name=\"Find the Main Address of a Person\","
				+ "</api/person-address/v1/find-person?page=0>;rel=\"Get all People\";type=\"GET-ALL\";name=\"Find All People\","
				+ "</api/person-address/v1/find-address?page=0>;rel=\"Get all Address\";type=\"GET-ALL\";name=\"Find All Addresses\""));
		
	    assertEquals("Person1", response.getName());
	    assertEquals("000.000.000-01", response.getCpf());
	    assertEquals("1990-12-21", response.getDateBirth());
	    assertEquals(addressInput.mockVoList(), response.getAddresses());
	}

	@Test
	void testFindMainAddressByPerson() {
		Person personEntity = personInput.mockEntity(1);
		
		AddressVo addressEntity = addressInput.mockVo(1);
		
		when(addressService.findByMainAddress(1L)).thenReturn(addressEntity);
		when(repository.findByCpf(personEntity.getCpf())).thenReturn(personEntity);
		
		PersonVo personResponse = service.findByCpf(personEntity.getCpf());

		assertNotNull(personResponse.getKey());
		assertNotNull(personResponse.getName());
		assertNotNull(personResponse.getCpf());
		assertNotNull(personResponse.getDateBirth());
		assertNotNull(personResponse.getAddresses());

	    assertEquals("Person1", personResponse.getName());
	    assertEquals("000.000.000-01", personResponse.getCpf());
	    assertEquals("1990-12-21", personResponse.getDateBirth());
	    assertEquals(addressInput.mockVoList(), personResponse.getAddresses());
		
	    AddressVo addressResponse = addressService.findByMainAddress(addressEntity.getKey());

	    assertNotNull(addressResponse.getKey());
		assertNotNull(addressResponse.getCep());
		assertNotNull(addressResponse.getStreet());
		assertNotNull(addressResponse.getCity());
		assertNotNull(addressResponse.getMainAddress());
		
		assertEquals("60823-111", addressResponse.getCep());
        assertEquals("rua1", addressResponse.getStreet());
        assertEquals(1, addressResponse.getNumber());
        assertEquals("cidade1", addressResponse.getCity());
        assertEquals(true, addressResponse.getMainAddress());
	}

	@Test
	void testInsertNewAddress() {
		Person entity = personInput.mockEntity(1);
		
		AddressVo addressEntity = addressInput.mockVo(1);
		AddressVo addressPersisted = addressEntity;
		
		when(repository.findByCpf(entity.getCpf())).thenReturn(entity);
		when(addressService.save(addressEntity)).thenReturn(addressPersisted);
		
		PersonVo personResponse = service.findByCpf(entity.getCpf());
		
		assertNotNull(personResponse.getKey());
		assertNotNull(personResponse.getName());
		assertNotNull(personResponse.getCpf());
		assertNotNull(personResponse.getDateBirth());
		assertNotNull(personResponse.getAddresses());

	    assertEquals("Person1", personResponse.getName());
	    assertEquals("000.000.000-01", personResponse.getCpf());
	    assertEquals("1990-12-21", personResponse.getDateBirth());
	    assertEquals(addressInput.mockVoList(), personResponse.getAddresses());
		
		addressService.save(addressEntity);
		
		assertNotNull(addressPersisted.getKey());
		assertNotNull(addressPersisted.getCep());
		assertNotNull(addressPersisted.getStreet());
		assertNotNull(addressPersisted.getCity());
		assertNotNull(addressPersisted.getMainAddress());
		
		assertEquals("60823-111", addressPersisted.getCep());
        assertEquals("rua1", addressPersisted.getStreet());
        assertEquals(1, addressPersisted.getNumber());
        assertEquals("cidade1", addressPersisted.getCity());
        assertEquals(true, addressPersisted.getMainAddress());
		
	}

	@Test
	void testDelete() {
		Person entity = personInput.mockEntity(1);
		
		when(repository.findByCpf(entity.getCpf())).thenReturn(entity);
		
		service.delete(entity.getCpf());
	}

}
