package com.attornatus.attornatus_assessment.unittests.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.attornatus.attornatus_assessment.data.vo.v1.AddressVo;
import com.attornatus.attornatus_assessment.mapper.AddressMapper;
import com.attornatus.attornatus_assessment.model.Address;
import com.attornatus.attornatus_assessment.unittests.mocks.MockAddress;

public class VoMapperAddressTeste {

	MockAddress inputObject;

    @BeforeEach
    public void setUp() {
        inputObject = new MockAddress();
    }

    @Test
    public void parseEntityToVOTest() {
        AddressVo output = AddressMapper.entityToVo(inputObject.mockEntity());
        assertEquals(Long.valueOf(0L), output.getKey());
        assertEquals("60823-110", output.getCep());
        assertEquals("rua0", output.getStreet());
        assertEquals(0, output.getNumber());
        assertEquals("cidade0", output.getCity());
        assertEquals(true, output.getMainAddress());
    }

    @Test
    public void parseEntityListToVOListTest() {
        List<AddressVo> outputList = 
        		AddressMapper.entityToVoList(inputObject.mockEntityList());
        AddressVo outputZero = outputList.get(0);
       
        assertEquals(Long.valueOf(0L), outputZero.getKey());
        assertEquals("60823-110", outputZero.getCep());
        assertEquals("rua0", outputZero.getStreet());
        assertEquals(0, outputZero.getNumber());
        assertEquals("cidade0", outputZero.getCity());
        assertEquals(true, outputZero.getMainAddress());
        
        AddressVo outputSeven = outputList.get(7);
       
        assertEquals(Long.valueOf(7L), outputSeven.getKey());
        assertEquals("60823-117", outputSeven.getCep());
        assertEquals("rua7", outputSeven.getStreet());
        assertEquals(7, outputSeven.getNumber());
        assertEquals("cidade7", outputSeven.getCity());
        assertEquals(true, outputSeven.getMainAddress());
        
        AddressVo outputNine = outputList.get(9);
        
        assertEquals(Long.valueOf(9L), outputNine.getKey());
        assertEquals("60823-119", outputNine.getCep());
        assertEquals("rua9", outputNine.getStreet());
        assertEquals(9, outputNine.getNumber());
        assertEquals("cidade9", outputNine.getCity());
        assertEquals(true, outputNine.getMainAddress());
    }
    
    @Test
    public void parseVOToEntityTest() {
    	Address output = 
    			AddressMapper.voToEntity(inputObject.mockVo());
    	assertEquals(Long.valueOf(0L), output.getId());
    	assertEquals("60823-110", output.getCep());
        assertEquals("rua0", output.getStreet());
        assertEquals(0, output.getNumber());
        assertEquals("cidade0", output.getCity());
        assertEquals(true, output.getMainAddress());
    }

    @Test
    public void parserVOListToEntityListTest() {
        List<Address> outputList =
        		AddressMapper.voToEntityList(inputObject.mockVoList());
        Address outputZero = outputList.get(0);
        
        assertEquals(Long.valueOf(0L), outputZero.getId());
        assertEquals("60823-110", outputZero.getCep());
        assertEquals("rua0", outputZero.getStreet());
        assertEquals(0, outputZero.getNumber());
        assertEquals("cidade0", outputZero.getCity());
        assertEquals(true, outputZero.getMainAddress());
        
        Address outputSeven = outputList.get(7);
       
        assertEquals(Long.valueOf(7L), outputSeven.getId());
        assertEquals("60823-117", outputSeven.getCep());
        assertEquals("rua7", outputSeven.getStreet());
        assertEquals(7, outputSeven.getNumber());
        assertEquals("cidade7", outputSeven.getCity());
        assertEquals(true, outputSeven.getMainAddress());
        
        Address outputNine = outputList.get(9);
        
        assertEquals(Long.valueOf(9L), outputNine.getId());
        assertEquals("60823-119", outputNine.getCep());
        assertEquals("rua9", outputNine.getStreet());
        assertEquals(9, outputNine.getNumber());
        assertEquals("cidade9", outputNine.getCity());
        assertEquals(true, outputNine.getMainAddress());
    }
	
}
