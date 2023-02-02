package com.attornatus.attornatus_assessment.unittests.mocks;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.attornatus.attornatus_assessment.data.vo.v1.PersonVo;
import com.attornatus.attornatus_assessment.model.Person;

public class MockPerson {

	MockAddress address = new MockAddress();

	public Person mockEntity() {
        return mockEntity(0);
    }
    
    public PersonVo mockVo() {
        return mockVo(0);
    }
    
    public List<Person> mockEntityList() {
        List<Person> people = IntStream.range(0, 10)
        		.mapToObj(i -> mockEntity(i))
        		.collect(Collectors.toList());
        return people;
    }

    public List<PersonVo> mockVoList() {
        List<PersonVo> people = IntStream.range(0, 10)
        		.mapToObj(i -> mockVo(i))
        		.collect(Collectors.toList());
        return people;
    }
    
    public Person mockEntity(Integer number) {
    	Person person = Person.builder()
    			.id(number.longValue())
    			.name("Person" + number)
    			.cpf("000.000.000-0" + number)
        		.dateBirth(LocalDate.parse("1990-12-2"+number))
        		.addresses(address.mockEntityList())
        		.build();
        
        return person;
    }

    public PersonVo mockVo(Integer number) {
    	PersonVo person = PersonVo.builder()
    			.key(number.longValue())
    			.name("Person" + number)
    			.cpf("000.000.000-0" + number)
        		.dateBirth("1990-12-2"+number)
        		.addresses(address.mockVoList())
        		.build();
        
        return person;
    }

}	

