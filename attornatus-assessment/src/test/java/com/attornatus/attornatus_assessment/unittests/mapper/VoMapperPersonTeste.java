package com.attornatus.attornatus_assessment.unittests.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.attornatus.attornatus_assessment.data.vo.v1.PersonVo;
import com.attornatus.attornatus_assessment.mapper.PersonMapper;
import com.attornatus.attornatus_assessment.model.Person;
import com.attornatus.attornatus_assessment.unittests.mocks.MockAddress;
import com.attornatus.attornatus_assessment.unittests.mocks.MockPerson;

public class VoMapperPersonTeste {

	MockPerson inputObject;
	MockAddress address;

    @BeforeEach
    public void setUp() {
        inputObject = new MockPerson();
        address = new MockAddress();
    }

    @Test
    public void parseEntityToVoTest() {
        PersonVo output = 
        		PersonMapper.entityToVo(inputObject.mockEntity());
        assertEquals(Long.valueOf(0L), output.getKey());
        assertEquals("Person0", output.getName());
        assertEquals("000.000.000-00", output.getCpf());
        assertEquals("1990-12-20", output.getDateBirth());
        assertEquals(address.mockVoList(), output.getAddresses());
    }
    
    @Test
    public void parseVoToEntityTest() {
        Person output = 
        		PersonMapper.voToEntity(inputObject.mockVo());
        assertEquals(Long.valueOf(0L), output.getId());
        assertEquals("Person0", output.getName());
        assertEquals("000.000.000-00", output.getCpf());
        assertEquals(LocalDate.parse("1990-12-20"), output.getDateBirth());
        assertEquals(address.mockEntityList(), output.getAddresses());
    }
}
