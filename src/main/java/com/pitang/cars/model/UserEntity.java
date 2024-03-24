package com.pitang.cars.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "usr")
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	Long id;

	@Column(name = "first_name ", nullable = true)
	String firstName ;

	@Column(name = "last_lame ", nullable = true)
	String lastName ;

	@Column(name = "email", nullable = true)
	String email;
	
	
	@Column(name = "birthday ", nullable = true)
	LocalDate birthday ;

	@Column(name = "login  ", nullable = true)
	String login  ;

	@Column(name = "password", nullable = true)
	String password;
	
	@Column(name = "phone ", nullable = true)
	String phone ;
	
	@Column(name = "created_at ", nullable = true, updatable = false)
	LocalDate createdAt = LocalDate.now() ;
	
	@Column(name = "last_login ", nullable = true)
	LocalDate lastLogin ;


	@OneToMany
	@Column(name = "cars ", nullable = true)
	List<CarEntity> cars ;

	

}
