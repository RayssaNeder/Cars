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

import com.pitang.cars.model.CarEntity;
import com.pitang.cars.repository.CarRepository;
import com.pitang.cars.service.CarServiceImpl;

import lombok.SneakyThrows;

@MockitoSettings(strictness = Strictness.LENIENT)
@ExtendWith(MockitoExtension.class)
public class CarServiceImplTest {
	
	  private static final int YEAR = 2018;

	private static final String MODEL = "Audi";

	private static final String COLOR = "White";

	private static final String LICENSE_PLATE = "PDV-0625";

	@InjectMocks CarServiceImpl carServiceImpl;

	  @Mock CarRepository carRepository;

	
	@Test
	  @SneakyThrows
	  public void testCreate() {

	    var car =
	        new CarEntity();
	    car.setColor(COLOR);
	    car.setLicensePlate(LICENSE_PLATE);
	    car.setModel(MODEL);
	    car.setYear(YEAR);
	   
	    // Given
	    doReturn(Optional.empty()).when(carRepository).findByLicensePlate(LICENSE_PLATE);

	    doReturn(car).when(carRepository).save(car);

	    var carReturned =
	        carServiceImpl.create(car);

	    assertEquals(carReturned.getLicensePlate(), LICENSE_PLATE);
	    assertEquals(carReturned.getModel(), MODEL);
	    assertEquals(carReturned.getYear(), 2018);
	    assertEquals(carReturned.getColor(), COLOR);
	  }
	
	
	@Test
	  @SneakyThrows
	  public void testRead() {

		 var car =
			        new CarEntity();
			    car.setColor(COLOR);
			    car.setLicensePlate(LICENSE_PLATE);
			    car.setModel(MODEL);
			    car.setYear(YEAR);

	    // Given
	    given(carRepository.findById(1L))
	        .willReturn(Optional.of(car));
	   

	    var carReturned = carServiceImpl.read(1L);

	    assertEquals(carReturned.getLicensePlate(), LICENSE_PLATE);
	    assertEquals(carReturned.getModel(), MODEL);
	    assertEquals(carReturned.getYear(), 2018);
	    assertEquals(carReturned.getColor(), COLOR);
	  }

	@Test
	  @SneakyThrows
	  public void testList() {

	 
		 var car =
			        new CarEntity();
			    car.setColor(COLOR);
			    car.setLicensePlate(LICENSE_PLATE);
			    car.setModel(MODEL);
			    car.setYear(YEAR);
			    
	   

	    var cars = Arrays.asList(car);


	    // Given
	    given(carRepository.findAll())
	        .willReturn(cars);

	    var carsReturned = carServiceImpl.list();

	    assertEquals(carsReturned.get(0).getColor(), COLOR);
	    assertEquals(carsReturned.get(0).getLicensePlate(), LICENSE_PLATE);
	    assertEquals(carsReturned.get(0).getYear(), YEAR);
	    assertEquals(carsReturned.get(0).getModel(), MODEL);
	   
	  }
	

	  @Test
	  @SneakyThrows
	  public void testDelete() {
	    assertDoesNotThrow(() -> carServiceImpl.delete(1L));
	  }

}
