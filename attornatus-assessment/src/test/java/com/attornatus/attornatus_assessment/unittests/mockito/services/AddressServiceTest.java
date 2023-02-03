package com.attornatus.attornatus_assessment.unittests.mockito.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
import com.attornatus.attornatus_assessment.model.Address;
import com.attornatus.attornatus_assessment.repositories.AddressRepository;
import com.attornatus.attornatus_assessment.services.AddressService;
import com.attornatus.attornatus_assessment.unittests.mocks.MockAddress;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class AddressServiceTest {

	MockAddress input;
	
	@InjectMocks
	private AddressService service;
	
	@Mock
	AddressRepository repository;

	@BeforeEach
	void setUpMokcs() throws Exception {
		input = new MockAddress();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testFindById() {
		Address entity = input.mockEntity(1);
		
		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		AddressVo response = service.findById(1L);
		
        assertEquals("60823-111", response.getCep());
        assertEquals("rua1", response.getStreet());
        assertEquals(1, response.getNumber());
        assertEquals("cidade1", response.getCity());
        assertEquals(true, response.getMainAddress());
	}

	@Test
	void testFindByMainAddress() {
		Address addressEntity = input.mockEntity(1);
		
		when(repository.findMainAddress(1L)).thenReturn(addressEntity);
	    AddressVo addressResponse = service.findByMainAddress(1L);

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
	void testSave() {
		Address entity = input.mockEntity(1);
		Address persisted = entity;
		
		AddressVo vo = input.mockVo(1);
		
		when(repository.save(entity)).thenReturn(persisted);

		service.save(vo);
		
		assertNotNull(persisted);
		assertNotNull(persisted.getId());
		assertNotNull(persisted.getCep());
		assertNotNull(persisted.getStreet());
		assertNotNull(persisted.getCity());
		assertNotNull(persisted.getMainAddress());
		
		assertEquals("60823-111", persisted.getCep());
        assertEquals("rua1", persisted.getStreet());
        assertEquals(1, persisted.getNumber());
        assertEquals("cidade1", persisted.getCity());
        assertEquals(true, persisted.getMainAddress());
	}

	@Test
	void testDelete() {
		Address entity = input.mockEntity(1);
		
		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		
		service.delete(entity.getId());
	}

}
