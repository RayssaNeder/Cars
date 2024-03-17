package com.pitang.cars.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pitang.cars.model.CarEntity;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Integer> {



  void deleteById(Long id);

  Optional<CarEntity>  findById(Long id);

  Optional<CarEntity> findByLicensePlate(String licensePlate);
}
