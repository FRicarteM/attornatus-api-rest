package com.attornatus.attornatus_assessment.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "address")
public class Address implements Serializable {

	private static final long serialVersionUID = -8359642297641665074L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, length = 9)
	private String cep;
	@Column(nullable = false, length = 150)
	private String street;
	@Column(length = 5)
	private int number;
	@Column(nullable = false, length = 68)
	private String city;
	@Column(name = "main_address", nullable = false)
	private Boolean mainAddress;
	

}
