package com.pitang.cars.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pitang.cars.exception.AlreadyExistsException;
import com.pitang.cars.exception.NotFoundException;
import com.pitang.cars.model.CarEntity;
import com.pitang.cars.model.UserEntity;
import com.pitang.cars.repository.CarRepository;
import com.pitang.cars.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	private CarRepository carRepository;

	public UserServiceImpl(UserRepository userRepository, CarRepository carRepository) {
		this.userRepository = userRepository;
		this.carRepository = carRepository;
	}

	@Transactional
	public UserEntity create(UserEntity user) {

		Optional<UserEntity> existingUserOptional = userRepository.findByLogin(user.getLogin());
		if (existingUserOptional.isPresent()) {
			throw new AlreadyExistsException(String.format(user.getLogin()));

		}
       if(user.getCars() != null)
		for (CarEntity car : user.getCars()) {
			carRepository.save(car);

		}

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		UserEntity userEntity = userRepository.save(user);
		return userEntity;
	}

	@Override
	public UserEntity read(Long id) {
		return userRepository.findById(id).get();
	}

	@Override
	@Transactional

	public UserEntity update(Long id, UserEntity updatedUser) {
		Optional<UserEntity> existingUserOptional = userRepository.findById(id);
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		if (!existingUserOptional.isPresent()) {
			throw new NotFoundException(String.format("User com id '%s' não encontrado", id));

		}

		UserEntity existingUser = existingUserOptional.get();
		existingUser.setEmail(updatedUser.getEmail());
		existingUser.setFirstName(updatedUser.getFirstName());
		existingUser.setLastName(updatedUser.getLastName());
		existingUser.setPhone(updatedUser.getPhone());
		existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
		existingUser.setPassword(updatedUser.getPassword());

		return userRepository.saveAndFlush(existingUser);

	}

	@Override
	@Transactional
	public void delete(Long id) {
		userRepository.deleteById(id);

	}

	@Override
	public List<UserEntity> list() {
		return userRepository.findAll();
	}

}
