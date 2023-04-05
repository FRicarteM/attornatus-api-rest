package com.attornatus.attornatus_assessment.data.vo.v1;

import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Relation(collectionRelation = "List of Addresses")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonPropertyOrder({"id", "cep", "street", "number", "city"})
public class AddressVo extends RepresentationModel<AddressVo> implements Serializable {

	private static final long serialVersionUID = 6640860057726667460L;
	@JsonProperty(value = "id")
	@Schema(type = "Long", example = "01")
	private Long key;
	@Pattern(regexp = "([0-9]{5}-[0-9]{3})",
			message = "The postal code is out of standard")
	@Schema(type = "String", example = "60000-555", format = "cep")
	private String cep;
	@Schema(type = "String", example = "Street of April")
	private String street;
	@Schema(type = "int", example = "222")
	private int number;
	@Schema(type = "String", example = "Fortaleza")
	private String city;
	@Schema(type = "Boolean", example = "true")
	private Boolean mainAddress;
	

}
