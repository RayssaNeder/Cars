package com.pitang.cars;

import java.util.Arrays;
import java.util.Optional;
import static org.mockito.Mockito.doReturn;
import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.pitang.cars.model.UserEntity;
import com.pitang.cars.repository.UserRepository;
import com.pitang.cars.service.UserServiceImpl;

import lombok.SneakyThrows;

@MockitoSettings(strictness = Strictness.LENIENT)
@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
	
	  private static final String PSSWORD_TEST = "psswordTest";

	private static final String LOGIN_TEST = "loginTest";

	private static final String PHONE_TEST = "phoneTest";

	private static final String EMAIL_TEST = "email@test.com.br";

	private static final String LAST_NAME = "lastName";

	private static final String FIRST_NAME = "firstName";


	@InjectMocks UserServiceImpl userServiceImpl;

	  @Mock UserRepository carRepository;

	
	@Test
	  @SneakyThrows
	  public void testCreate() {

	    var user =
	        new UserEntity();
	    user.setFirstName(FIRST_NAME);
	    user.setLastName(LAST_NAME);
	    user.setLogin(LOGIN_TEST);
	    user.setPassword(PSSWORD_TEST);
	    user.setPhone(PHONE_TEST);
	    user.setEmail(EMAIL_TEST);
	   
	    // Given
	   // doReturn(Optional.empty()).when(carRepository).findByLicensePlate(LICENSE_PLATE);

	    doReturn(user).when(carRepository).save(user);

	    var userReturned =
	        userServiceImpl.create(user);

	    assertEquals(userReturned.getFirstName(), FIRST_NAME);
	    assertEquals(userReturned.getLastName(), LAST_NAME);
	    assertEquals(userReturned.getEmail(), EMAIL_TEST);
	    assertEquals(userReturned.getPhone(), PHONE_TEST);
	    assertEquals(userReturned.getLogin(), LOGIN_TEST);
	    assertEquals(userReturned.getPassword(), PSSWORD_TEST);
	  }
	
	
	@Test
	  @SneakyThrows
	  public void testRead() {

		 var user =
			        new UserEntity();
		 user.setFirstName(FIRST_NAME);
		    user.setLastName(LAST_NAME);
		    user.setLogin(LOGIN_TEST);
		    user.setPassword(PSSWORD_TEST);
		    user.setPhone(PHONE_TEST);
		    user.setEmail(EMAIL_TEST);

	    // Given
	    given(carRepository.findById(1L))
	        .willReturn(Optional.of(user));
	   

	    var userReturned = userServiceImpl.read(1L);

	    assertEquals(userReturned.getFirstName(), FIRST_NAME);
	    assertEquals(userReturned.getLastName(), LAST_NAME);
	    assertEquals(userReturned.getEmail(), EMAIL_TEST);
	    assertEquals(userReturned.getPhone(), PHONE_TEST);
	    assertEquals(userReturned.getLogin(), LOGIN_TEST);
	    assertEquals(userReturned.getPassword(), PSSWORD_TEST);
	   
	  }

	@Test
	  @SneakyThrows
	  public void testList() {

	 
		 var user =
			        new UserEntity();
		 user.setFirstName(FIRST_NAME);
		    user.setLastName(LAST_NAME);
		    user.setLogin(LOGIN_TEST);
		    user.setPassword(PSSWORD_TEST);
		    user.setPhone(PHONE_TEST);
		    user.setEmail(EMAIL_TEST);

			    
	   

	    var users = Arrays.asList(user);


	    // Given
	    given(carRepository.findAll())
	        .willReturn(users);

	    var usersReturned = userServiceImpl.list();

	    assertEquals(usersReturned.get(0).getFirstName(), FIRST_NAME);
	    assertEquals(usersReturned.get(0).getLastName(), LAST_NAME);
	    assertEquals(usersReturned.get(0).getEmail(), EMAIL_TEST);
	    assertEquals(usersReturned.get(0).getPhone(), PHONE_TEST);
	    assertEquals(usersReturned.get(0).getLogin(), LOGIN_TEST);
	    assertEquals(usersReturned.get(0).getPassword(), PSSWORD_TEST);
	   
	  }
	

	  @Test
	  @SneakyThrows
	  public void testDelete() {
	    assertDoesNotThrow(() -> userServiceImpl.delete(1L));
	  }

}
