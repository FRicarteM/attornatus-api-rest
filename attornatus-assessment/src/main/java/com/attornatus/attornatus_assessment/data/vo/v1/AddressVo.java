package com.attornatus.attornatus_assessment.data.vo.v1;

import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonPropertyOrder({"id", "cep", "street", "number", "city"})
public class AddressVo extends RepresentationModel<AddressVo> implements Serializable {

	private static final long serialVersionUID = 6640860057726667460L;
	@JsonProperty(value = "id")
	private Long key;
	
	private String cep;
	
	private String street;
	
	private int number;
	
	private String city;
	
	private Boolean mainAddress;
	

}
