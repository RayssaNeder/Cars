package com.pitang.cars;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doReturn;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.pitang.cars.model.CarEntity;
import com.pitang.cars.model.UserEntity;
import com.pitang.cars.repository.CarRepository;
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
	
	  private static final int YEAR = 2018;

		private static final String MODEL = "Audi";

		private static final String COLOR = "White";

		private static final String LICENSE_PLATE = "PDV-0625";


	@InjectMocks UserServiceImpl userServiceImpl;

	  @Mock UserRepository userRepository;
	  
	  @Mock CarRepository carRepository;

	
	@Test
	  @SneakyThrows
	  public void testCreate() {
		
		CarEntity car =
		        new CarEntity();
		    car.setColor(COLOR);
		    car.setLicensePlate(LICENSE_PLATE);
		    car.setModel(MODEL);
		    car.setYear(YEAR);

		UserEntity user =
	        new UserEntity();
	    user.setFirstName(FIRST_NAME);
	    user.setLastName(LAST_NAME);
	    user.setLogin(LOGIN_TEST);
	    user.setPassword(PSSWORD_TEST);
	    user.setPhone(PHONE_TEST);
	    user.setEmail(EMAIL_TEST);
	    user.setCars(Arrays.asList(car));
	   
	    // Given
	    doReturn(Optional.empty()).when(carRepository).findByLicensePlate(LICENSE_PLATE);

	    doReturn(user).when(userRepository).save(user);

	    UserEntity userReturned =
	        userServiceImpl.create(user);

	    assertEquals(userReturned.getFirstName(), FIRST_NAME);
	    assertEquals(userReturned.getLastName(), LAST_NAME);
	    assertEquals(userReturned.getEmail(), EMAIL_TEST);
	    assertEquals(userReturned.getPhone(), PHONE_TEST);
	    assertEquals(userReturned.getLogin(), LOGIN_TEST);
	  }
	
	
	@Test
	  @SneakyThrows
	  public void testRead() {

		UserEntity user =
			        new UserEntity();
		 user.setFirstName(FIRST_NAME);
		    user.setLastName(LAST_NAME);
		    user.setLogin(LOGIN_TEST);
		    user.setPassword(PSSWORD_TEST);
		    user.setPhone(PHONE_TEST);
		    user.setEmail(EMAIL_TEST);

	    // Given
	    given(userRepository.findById(1L))
	        .willReturn(Optional.of(user));
	   

	    UserEntity userReturned = userServiceImpl.read(1L);

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

	 
		UserEntity user =
			        new UserEntity();
		 user.setFirstName(FIRST_NAME);
		    user.setLastName(LAST_NAME);
		    user.setLogin(LOGIN_TEST);
		    user.setPassword(PSSWORD_TEST);
		    user.setPhone(PHONE_TEST);
		    user.setEmail(EMAIL_TEST);

			    
	   

		    List<UserEntity> users = Arrays.asList(user);


	    // Given
	    given(userRepository.findAll())
	        .willReturn(users);

	    List<UserEntity> usersReturned = userServiceImpl.list();

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
