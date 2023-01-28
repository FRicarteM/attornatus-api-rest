package com.attornatus.attornatus_assessment.data.vo.v1;

import java.io.Serializable;
import java.util.List;

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
@JsonPropertyOrder({"id", "name", "cpf", "date_birth", "addresses"})
public class PersonVo extends RepresentationModel<PersonVo> implements Serializable {

	private static final long serialVersionUID = -1075851367822752420L;
	@JsonProperty(value = "id")
	private Long key;

	private String name;

	private String cpf;
	@JsonProperty(value = "date_birth")
	private String dateBirth;

	private List<AddressVo> addresses;
	
}
