package com.pitang.cars.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pitang.cars.model.UserEntity;
import com.pitang.cars.repository.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
    private UserRepository  userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		UserEntity user = userRepository.findByLogin(login);
		
		if (user.getLogin().equals(login)) {
			return new User(login, user.getPassword(),
					new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with login: " + login);
		}
	}
}