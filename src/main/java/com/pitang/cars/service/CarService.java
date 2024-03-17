package com.pitang.cars.service;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.pitang.cars.model.CarEntity;

public interface CarService {
	
	public CarEntity create(CarEntity car);

	  public CarEntity read(Long id);

	  public CarEntity update(Long id, CarEntity car);

	  public void delete(Long id);

	  public List<CarEntity> list();

}
