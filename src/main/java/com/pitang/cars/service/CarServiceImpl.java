package com.pitang.cars.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pitang.cars.exception.AlreadyExistsException;
import com.pitang.cars.exception.NotFoundException;
import com.pitang.cars.model.CarEntity;
import com.pitang.cars.repository.CarRepository;

@Service
@Transactional
public class CarServiceImpl implements CarService {

	private CarRepository carRepository;

	public CarServiceImpl(CarRepository carRepository) {
		this.carRepository = carRepository;
	}

	@Override
	public CarEntity create(CarEntity car) {
		Optional<CarEntity> carOptional = carRepository.findByLicensePlate(car.getLicensePlate());

		if (carOptional.isPresent()) {
			throw new AlreadyExistsException(String.format(
		              "Carro com placa '%s' já cadastrado",
		              car.getLicensePlate()));
		}

		CarEntity carEntity = carRepository.save(car);
		return carEntity;
	}

	@Override
	public CarEntity read(Long id) {
		return carRepository.findById(id).get();
	}

	@Override
	public CarEntity update(Long id, CarEntity updatedCar) {
		Optional<CarEntity> existingCarOptional = carRepository.findById(id);

		if (!existingCarOptional.isPresent()) {
			throw new NotFoundException(String.format(
		              "Carro com id '%s' não encontrado",
		              id));
			
			
			
		}

		CarEntity existingCar = existingCarOptional.get();
		existingCar.setColor(updatedCar.getColor());
		existingCar.setLicensePlate(updatedCar.getLicensePlate());
		existingCar.setYear(updatedCar.getYear());
		existingCar.setModel(updatedCar.getModel());

		return carRepository.saveAndFlush(existingCar);

	}

	@Override
	public void delete(Long id) {
		carRepository.deleteById(id);

	}

	@Override
	public List<CarEntity> list() {
		return carRepository.findAll();
	}

}
