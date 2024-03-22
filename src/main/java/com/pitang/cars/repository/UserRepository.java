package com.pitang.cars.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pitang.cars.model.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {



  void deleteById(Long id);

  Optional<UserEntity>  findById(Long id);
  
  Optional<UserEntity> findByLogin(String username);
  

}
