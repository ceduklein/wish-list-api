package br.senai.sc.wishlistapi.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.senai.sc.wishlistapi.controller.dto.AuthRequestDTO;
import br.senai.sc.wishlistapi.controller.dto.PasswordDTO;
import br.senai.sc.wishlistapi.controller.dto.UserDTO;
import br.senai.sc.wishlistapi.model.entity.User;
import br.senai.sc.wishlistapi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/users")
@Tag(name = "Users")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@PostMapping("/signup")
	@Operation(summary = "Create User")
	public ResponseEntity<?> createUser(@RequestBody UserDTO user) {
		try {
			User savedUser = service.save(user);
			return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PostMapping("/signin")
	@Operation(summary = "Authenticate User")
	public ResponseEntity<?> authenticateUser(@RequestBody AuthRequestDTO dto ) {
		try {
			Optional<User> user = service.auth(dto.getEmail(), dto.getPassword());
			return ResponseEntity.ok(user);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping
	@Operation(summary = "Get All Users")
	public ResponseEntity<?> getUsers() {
		try {
			List<User> users = service.findAll();
			return ResponseEntity.ok(users);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping("{id}")
	@Operation(summary = "Get User By Id")
	public ResponseEntity<?> getUserById(@PathVariable("id") UUID id) {
		try {
			Optional<User> user = service.findById(id);
			return ResponseEntity.ok(user);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("{id}")
	@Operation(summary = "Update User")
	public ResponseEntity<?> updateUser(@PathVariable("id") UUID id, @RequestBody UserDTO user) {
		try {
			User updatedUser = service.update(id, user);
			return ResponseEntity.ok(updatedUser);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PatchMapping("/change-password/{id}")
	@Operation(summary = "Change Password")
	public ResponseEntity<?> changePassword(@PathVariable("id") UUID id, @RequestBody PasswordDTO dto) {
		try {
			service.changePassword(id, dto);
			return new ResponseEntity<>("Password changed successfully.", HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DeleteMapping("{id}")
	@Operation(summary = "Delete User")
	public ResponseEntity<?> deleteUser(@PathVariable("id") UUID id) {
		try {
			service.delete(id);
			return new ResponseEntity<>("Successfully deleted user account.", HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
		 
}
