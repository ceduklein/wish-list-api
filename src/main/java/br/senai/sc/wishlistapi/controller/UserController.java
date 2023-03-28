package br.senai.sc.wishlistapi.controller;

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
import br.senai.sc.wishlistapi.model.entity.User;
import br.senai.sc.wishlistapi.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@PostMapping("/signup")
	public ResponseEntity<?> createUser(@RequestBody User user) {
		try {
			User savedUser = service.save(user);
			return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody AuthRequestDTO dto ) {
		try {
			Optional<User> user = service.auth(dto.getEmail(), dto.getPassword());
			return ResponseEntity.ok(user);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> getUserById(@PathVariable("id") UUID id) {
		try {
			Optional<User> user = service.findById(id);
			return ResponseEntity.ok(user);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("{id}")
	public ResponseEntity<?> updateUser(@PathVariable("id") UUID id, @RequestBody User user) {
		try {
			User updatedUser = service.update(id, user);
			return ResponseEntity.ok(updatedUser);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PatchMapping("/change-password/{id}")
	public ResponseEntity<?> changePassword(@PathVariable("id") UUID id, @RequestBody PasswordDTO dto) {
		try {
			service.changePassword(id, dto);
			return new ResponseEntity<>("Password changed successfully.", HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") UUID id) {
		try {
			service.delete(id);
			return new ResponseEntity<>("Successfully deleted user account.", HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
		 
}
