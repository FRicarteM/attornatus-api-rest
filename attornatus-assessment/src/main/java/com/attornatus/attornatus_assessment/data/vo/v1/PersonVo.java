package com.attornatus.attornatus_assessment.data.vo.v1;

import java.io.Serializable;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
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
	@Schema(type = "Long", example = "01")
	private Long key;
	@Schema(type = "String", example = "Fábio Magalhães")
	private String name;
	@Pattern(regexp = "([0-9]{3}[.]?[0-9]{3}[.]?[0-9]{3}-[0-9]{2})|([0-9]{11})"
			,message = "The format of CPF is wrong, please use this format XXX.XXX.XXX-XX.")
	@Schema(type = "String", example = "000.000.000-00", format = "cpf")
	private String cpf;
	@JsonProperty(value = "date_birth")
	@Schema(type = "String", example = "1999-12-01", format = "date")
	private String dateBirth;
	private List<AddressVo> addresses;
	
}
