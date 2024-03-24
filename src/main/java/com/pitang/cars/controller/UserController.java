package com.pitang.cars.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pitang.cars.model.CustomUserDetails;
import com.pitang.cars.model.UserEntity;
import com.pitang.cars.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(value = "UserController", description = "Controlador de Usuários")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/users")
    @ApiOperation(value = "Obter uma lista de usuários")
	public List<UserEntity> listUser() {
		return userService.list();
	}

	@PostMapping("/users")
    @ApiOperation(value = "Cria um novo usuário")
	public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user) {
		return new ResponseEntity<>(userService.create(user), HttpStatus.CREATED);

	}

	@PutMapping("/users/{id}")
    @ApiOperation(value = "Atualzar um usuário")
	public ResponseEntity<UserEntity> updateUser(@PathVariable Long id, @RequestBody UserEntity updatedUser) {
		return new ResponseEntity<>(userService.update(id, updatedUser), HttpStatus.OK);

	}

	@GetMapping("/users/{id}")
    @ApiOperation(value = "Obter um usuário pelo ID")
	public ResponseEntity<UserEntity> getUserById(@PathVariable Long id) {
		UserEntity user = userService.read(id);
		return ResponseEntity.ok(user);
	}

	@DeleteMapping("/users/{id}")
    @ApiOperation(value = "Deletar um usuário")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
		userService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

	@GetMapping("/me")
    @ApiOperation(value = "Obtem as informações de um usuário logado")
	public ResponseEntity<?> getUserProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            return ResponseEntity.ok(userDetails);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não autenticado ou informações indisponíveis.");
        }
    }

}
