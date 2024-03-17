package com.pitang.cars.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pitang.cars.exception.NotFoundException;
import com.pitang.cars.model.UserEntity;
import com.pitang.cars.repository.CarRepository;
import com.pitang.cars.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	private CarRepository carRepository;

	public UserServiceImpl(UserRepository userRepository, CarRepository carRepository) {
		this.userRepository = userRepository;
		this.carRepository = carRepository;
	}

	@Override
	public UserEntity create(UserEntity user) {
			    
			    user.getCars().forEach(
			        car -> {
			          if (!carRepository.findByLicensePlate(car.getLicensePlate()).isPresent()) {
			        	  carRepository.save(car);
			          }
			        });
	
			    UserEntity userEntity = userRepository.save(user);
		return userEntity;
	}

	@Override
	public UserEntity read(Long id) {
		return userRepository.findById(id).get();
	}

	@Override
	public UserEntity update(Long id, UserEntity updatedUser) {
		Optional<UserEntity> existingUserOptional = userRepository.findById(id);

		if (!existingUserOptional.isPresent()) {
			throw new NotFoundException(String.format(
		              "User com id '%s' não encontrado",
		              id));
			
			
			
		}

		UserEntity existingUser = existingUserOptional.get();
		existingUser.setEmail(updatedUser.getEmail());
		existingUser.setFirstName(updatedUser.getFirstName());
		existingUser.setLastName(updatedUser.getLastName());
		existingUser.setPhone(updatedUser.getPhone());
		existingUser.setLogin(updatedUser.getLogin());
		existingUser.setPassword(updatedUser.getPassword());

		return userRepository.saveAndFlush(existingUser);

	}

	@Override
	public void delete(Long id) {
		userRepository.deleteById(id);

	}

	@Override
	public List<UserEntity> list() {
		return userRepository.findAll();
	}

}
