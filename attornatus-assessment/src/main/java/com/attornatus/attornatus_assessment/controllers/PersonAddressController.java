package com.attornatus.attornatus_assessment.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.attornatus.attornatus_assessment.data.vo.v1.AddressVo;
import com.attornatus.attornatus_assessment.data.vo.v1.PersonVo;
import com.attornatus.attornatus_assessment.exceptions.ExceptionResponse;
import com.attornatus.attornatus_assessment.services.AddressService;
import com.attornatus.attornatus_assessment.services.PersonService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/person-address/v1")
@Tag(name = "Person-Addresses", description = "EndPoint for managing"
		+ " People ande your addresses")
public class PersonAddressController {

	@Autowired
	PersonService personService;
	
	@Autowired
	AddressService addressService;
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/create")
	@Operation(summary = "Create a Registration",
	description = "Create a registration for a person",
	tags = {"Create a Registration"},
	responses = {@ApiResponse( description = "Created",
		responseCode = "201", content = { @Content(mediaType = "application/json", array = @ArraySchema(
				schema = @Schema(implementation = PersonVo.class)))}),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = {@Content(
					mediaType = "application/json", array = @ArraySchema(
							schema = @Schema(implementation = ExceptionResponse.class)))}),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = {@Content(
					mediaType = "application/json", array = @ArraySchema(
							schema = @Schema(implementation = ExceptionResponse.class)))})})
	public void create(@RequestBody @Valid PersonVo personVo) {
		personService.save(personVo);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/insert-new-address/{cpf}")
	@Operation(summary = "Create a Registration",
	description = "Create a registration for a Address",
	tags = {"Create a Registration"},
	responses = {@ApiResponse( description = "Created",
		responseCode = "201", content = { @Content(mediaType = "application/json", array = @ArraySchema(
				schema = @Schema(implementation = PersonVo.class)))}),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = {@Content(
					mediaType = "application/json", array = @ArraySchema(
							schema = @Schema(implementation = ExceptionResponse.class)))}),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = {@Content(
					mediaType = "application/json", array = @ArraySchema(
							schema = @Schema(implementation = ExceptionResponse.class)))})})
	public void insertNewAddress(@PathVariable(value = "cpf") String cpf, @RequestBody @Valid AddressVo addressVo) {
		personService.insertNewAddress(cpf, addressVo);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@PutMapping("/update-person")
	@Operation(summary = "Update a Registration",
	description = "Update a registration for a person",
	tags = {"Update a Registration"},
	responses = {@ApiResponse( description = "Success",
		responseCode = "200", content = { @Content(mediaType = "application/json", array = @ArraySchema(
				schema = @Schema(implementation = PersonVo.class)))}),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = {@Content(
					mediaType = "application/json", array = @ArraySchema(
							schema = @Schema(implementation = ExceptionResponse.class)))}),
			@ApiResponse(description = "Not Found", responseCode = "404", content = {@Content(  
					mediaType = "application/json", array = @ArraySchema(
							schema = @Schema(implementation = ExceptionResponse.class)))}),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = {@Content(
					mediaType = "application/json", array = @ArraySchema(
							schema = @Schema(implementation = ExceptionResponse.class)))})})
	public void update (@RequestBody @Valid PersonVo personVo) {
		personService.updatePerson(personVo);
	}

	@ResponseStatus(HttpStatus.OK)
	@PutMapping("/update-person-address")
	@Operation(summary = "Update a Registration",
	description = "Update a registration for a person and your address",
	tags = {"Update a Registration"},
	responses = {@ApiResponse( description = "Success",
		responseCode = "200",  content = { @Content(mediaType = "application/json", array = @ArraySchema(
				schema = @Schema(implementation = PersonVo.class)))}),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = {@Content(
					mediaType = "application/json", array = @ArraySchema(
							schema = @Schema(implementation = ExceptionResponse.class)))}),
			@ApiResponse(description = "Not Found", responseCode = "404", content = {@Content(  
					mediaType = "application/json", array = @ArraySchema(
							schema = @Schema(implementation = ExceptionResponse.class)))}),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = {@Content(
					mediaType = "application/json", array = @ArraySchema(
							schema = @Schema(implementation = ExceptionResponse.class)))})})
	public void updatePersonAddress (@RequestBody @Valid PersonVo personVo) {
			personService.update(personVo);
	}

	@ResponseStatus(HttpStatus.OK)
	@PutMapping("/update-address")
	@Operation(summary = "Update a Registration",
	description = "Update a registration for a Address",
	tags = {"Update a Registration"},
	responses = {@ApiResponse( description = "Success",
		responseCode = "200", content = { @Content(mediaType = "application/json", array = @ArraySchema(
				schema = @Schema(implementation = PersonVo.class)))}),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = {@Content(
					mediaType = "application/json", array = @ArraySchema(
							schema = @Schema(implementation = ExceptionResponse.class)))}),
			@ApiResponse(description = "Not Found", responseCode = "404", content = {@Content(  
					mediaType = "application/json", array = @ArraySchema(
							schema = @Schema(implementation = ExceptionResponse.class)))}),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = {@Content(
					mediaType = "application/json", array = @ArraySchema(
							schema = @Schema(implementation = ExceptionResponse.class)))})})
	public void updateAddress (@RequestBody @Valid AddressVo addressVo) {
		addressService.update(addressVo);
	}

	@ResponseStatus(HttpStatus.OK)
	@PatchMapping("/update-main-address/{cpf}/{id}")
	@Operation(summary = "Update a specific field",
	description = "Update specific field, main Address",
	tags = {"Update a Registration"},
	responses = {@ApiResponse( description = "Success",
		responseCode = "200", content = { @Content(mediaType = "application/json", array = @ArraySchema(
				schema = @Schema(implementation = PersonVo.class)))}),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = {@Content(
					mediaType = "application/json", array = @ArraySchema(
							schema = @Schema(implementation = ExceptionResponse.class)))}),
			@ApiResponse(description = "Not Found", responseCode = "404", content = {@Content(  
					mediaType = "application/json", array = @ArraySchema(
							schema = @Schema(implementation = ExceptionResponse.class)))}),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = {@Content(
					mediaType = "application/json", array = @ArraySchema(
							schema = @Schema(implementation = ExceptionResponse.class)))})})
	public void updateMainAddress(@PathVariable(value = "cpf") String cpf, @PathVariable(value = "id") Long id) {
		personService.enableOrDisableMainAddressByPerson(cpf, id);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/find/{cpf}")
	@Operation(summary = "Find for a Person",
	description = "Find for a person by yor CPF",
	tags = {"Find For"},
	responses = {@ApiResponse( description = "Success",
		responseCode = "200", content = { @Content(mediaType = "application/json", array = @ArraySchema(
				schema = @Schema(implementation = PersonVo.class)))}),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = {@Content(
					mediaType = "application/json", array = @ArraySchema(
							schema = @Schema(implementation = ExceptionResponse.class)))}),
			@ApiResponse(description = "Not Found", responseCode = "404", content = {@Content(  
					mediaType = "application/json", array = @ArraySchema(
							schema = @Schema(implementation = ExceptionResponse.class)))}),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = {@Content(
					mediaType = "application/json", array = @ArraySchema(
							schema = @Schema(implementation = ExceptionResponse.class)))})})
	public PersonVo findByCpf(@PathVariable(value = "cpf") String cpf) {
			return personService.findByCpf(cpf);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/find-main-address/{cpf}")
	@Operation(summary = "Find for a Main Address",
	description = "Find for a Main Address",
	tags = {"Find For"},
	responses = {@ApiResponse( description = "Success",
		responseCode = "200", content = { @Content(mediaType = "application/json", array = @ArraySchema(
				schema = @Schema(implementation = PersonVo.class)))}),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = {@Content(
					mediaType = "application/json", array = @ArraySchema(
							schema = @Schema(implementation = ExceptionResponse.class)))}),
			@ApiResponse(description = "Not Found", responseCode = "404", content = {@Content(  
					mediaType = "application/json", array = @ArraySchema(
							schema = @Schema(implementation = ExceptionResponse.class)))}),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = {@Content(
					mediaType = "application/json", array = @ArraySchema(
							schema = @Schema(implementation = ExceptionResponse.class)))})})
	public AddressVo findMainAddressByPerson(@PathVariable(value = "cpf") String cpf) {
			return personService.findMainAddressByPerson(cpf);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/find-person")
	@Operation(summary = "Find all People",
	description = "Find all People",
	tags = {"Find All"},
	responses = {@ApiResponse( description = "Success",
		responseCode = "200", content = { @Content(mediaType = "application/json", array = @ArraySchema(
				schema = @Schema(implementation = PersonVo.class)))}),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = {@Content(
					mediaType = "application/json", array = @ArraySchema(
							schema = @Schema(implementation = ExceptionResponse.class)))}),
			@ApiResponse(description = "Not Found", responseCode = "404", content = {@Content(  
					mediaType = "application/json", array = @ArraySchema(
							schema = @Schema(implementation = ExceptionResponse.class)))}),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = {@Content(
					mediaType = "application/json", array = @ArraySchema(
							schema = @Schema(implementation = ExceptionResponse.class)))})})
	public PagedModel<EntityModel<PersonVo>> findAllPerson(
			@RequestParam(value = "page", defaultValue = "0") Integer page) {
		return personService.findAll(page);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/find-address")
	@Operation(summary = "Find all Address",
	description = "Find all Address",
	tags = {"Find All"},
	responses = {@ApiResponse( description = "Success",
		responseCode = "200", content = { @Content(mediaType = "application/json", array = @ArraySchema(
				schema = @Schema(implementation = PersonVo.class)))}),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = {@Content(
					mediaType = "application/json", array = @ArraySchema(
							schema = @Schema(implementation = ExceptionResponse.class)))}),
			@ApiResponse(description = "Not Found", responseCode = "404", content = {@Content(  
					mediaType = "application/json", array = @ArraySchema(
							schema = @Schema(implementation = ExceptionResponse.class)))}),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = {@Content(
					mediaType = "application/json", array = @ArraySchema(
							schema = @Schema(implementation = ExceptionResponse.class)))})})
	public PagedModel<EntityModel<AddressVo>> findAllAddress(
			@RequestParam(value = "page", defaultValue = "0") Integer page) {
		return addressService.findAll(page);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping("/delete-person/{cpf}")
	@Operation(summary = "Remove a Person",
	description = "Remove a Person by your CPF",
	tags = {"Remove"},
	responses = {@ApiResponse( description = "No Content",
		responseCode = "204", content = { @Content(mediaType = "application/json", array = @ArraySchema(
				schema = @Schema(implementation = PersonVo.class)))}),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = {@Content(
					mediaType = "application/json", array = @ArraySchema(
							schema = @Schema(implementation = ExceptionResponse.class)))}),
			@ApiResponse(description = "Not Found", responseCode = "404", content = {@Content(  
					mediaType = "application/json", array = @ArraySchema(
							schema = @Schema(implementation = ExceptionResponse.class)))}),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = {@Content(
					mediaType = "application/json", array = @ArraySchema(
							schema = @Schema(implementation = ExceptionResponse.class)))})})
	public void deletePerson(@PathVariable(value = "cpf") String cpf) {
		personService.delete(cpf);
	}

	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping("/delete-address/{id}")
	@Operation(summary = "Remove a Address",
	description = "Remove a Address by your ID",
	tags = {"Remove"},
	responses = {@ApiResponse( description = "No Content",
		responseCode = "204", content = { @Content(mediaType = "application/json", array = @ArraySchema(
				schema = @Schema(implementation = PersonVo.class)))}),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = {@Content(
					mediaType = "application/json", array = @ArraySchema(
							schema = @Schema(implementation = ExceptionResponse.class)))}),
			@ApiResponse(description = "Not Found", responseCode = "404", content = {@Content(  
					mediaType = "application/json", array = @ArraySchema(
							schema = @Schema(implementation = ExceptionResponse.class)))}),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = {@Content(
					mediaType = "application/json", array = @ArraySchema(
							schema = @Schema(implementation = ExceptionResponse.class)))})})
	public void deleteAddress(@PathVariable(value = "id") Long id) {
		addressService.delete(id);
	}
}
