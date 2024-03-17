package com.pitang.cars.service;

import java.util.List;


import com.pitang.cars.model.UserEntity;

public interface UserService {
	
	public UserEntity create(UserEntity car);

	  public UserEntity read(Long id);

	  public UserEntity update(Long id, UserEntity car);

	  public void delete(Long id);

	  public List<UserEntity> list();

}
