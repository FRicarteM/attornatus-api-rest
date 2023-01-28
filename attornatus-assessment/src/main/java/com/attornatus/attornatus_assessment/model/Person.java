package com.attornatus.attornatus_assessment.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "person")
public class Person implements Serializable {

	private static final long serialVersionUID = -4127048653208276865L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false, unique = true)
	private String cpf;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Column(name = "date_birth",nullable = false)
	private LocalDate dateBirth;
	@ManyToMany
    @JoinTable(name = "person_address",
    joinColumns = @JoinColumn(name = "person_fk"),
    inverseJoinColumns = @JoinColumn(name = "address_fk"))
	private Set<Address> addresses;
	
}
