package com.pitang.cars.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pitang.cars.model.CarEntity;
import com.pitang.cars.service.CarService;


@RestController
@RequestMapping("/api/cars")
public class CarController {
	
	private final CarService carService;
	
	  public CarController(CarService carService) {
		    this.carService = carService;
		  }
    
    @GetMapping("")
    public List<CarEntity> listCar() {
        return carService.list();
    }
    
    @PostMapping("")
    public ResponseEntity<CarEntity> createCar(@RequestBody CarEntity car) {
        return new ResponseEntity<>(carService.create(car), HttpStatus.CREATED);

    }
    
    @PutMapping("/{id}")
    public ResponseEntity<CarEntity> updateCar(@PathVariable Long id, @RequestBody CarEntity updatedCar) {
        return new ResponseEntity<>(carService.update(id, updatedCar), HttpStatus.OK);

    }
    
    @GetMapping("/{id}")
    public ResponseEntity<CarEntity> getCarById(@PathVariable Long id) {
        CarEntity car = carService.read(id);
        return ResponseEntity.ok(car);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        carService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}
