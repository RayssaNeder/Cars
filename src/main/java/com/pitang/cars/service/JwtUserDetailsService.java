package com.pitang.cars.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pitang.cars.model.CustomUserDetails;
import com.pitang.cars.model.UserEntity;
import com.pitang.cars.repository.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
    private UserRepository  userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
	    Optional<UserEntity> user = userRepository.findByLogin(login);

	    if (user.isPresent()) {
	        UserEntity userEntity = user.get();
	        
	        Collection<? extends GrantedAuthority> authorities = null; 
	        boolean accountNonExpired = true; 
	        boolean accountNonLocked = true; 
	        boolean credentialsNonExpired = true; 
	        boolean enabled = true; 	        
	        
	        userEntity.setLastLogin( LocalDate.now());
	        userRepository.save(userEntity);
	        
	        return new CustomUserDetails(userEntity.getLogin(), userEntity.getPassword(),
	                userEntity.getFirstName(), userEntity.getLastName(), userEntity.getEmail(),
	                userEntity.getPhone(), userEntity.getCreatedAt(), userEntity.getLastLogin(), accountNonExpired, accountNonLocked,credentialsNonExpired, enabled, authorities ); 
	    } else {
	        throw new UsernameNotFoundException("User not found with login: " + login);
	    }
	}
}