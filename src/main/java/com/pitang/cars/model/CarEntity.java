package com.pitang.cars.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "car")
public class CarEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	Long id;

	@Column(name = "year")
	int year;

	@Column(name = "license_plate", nullable = true)
	String licensePlate;

	@Column(name = "model", nullable = true)
	String model;

	@Column(name = "color", nullable = true)
	String color;

}
