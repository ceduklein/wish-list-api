package br.senai.sc.wishlistapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.senai.sc.wishlistapi.controller.dto.AuthRequestDTO;
import br.senai.sc.wishlistapi.model.entity.User;
import br.senai.sc.wishlistapi.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@PostMapping("/signup")
	public ResponseEntity<?> createUser(@RequestBody User u) {
		User user = new User();
		user.setName(u.getName());
		user.setEmail(u.getEmail());
		user.setBirthdate(u.getBirthdate());
		user.setPassword(u.getPassword());
		
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
			User authUser = service.auth(dto.getEmail(), dto.getPassword());
			return ResponseEntity.ok(authUser);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
		 
	
	@GetMapping
	public String Test() {
		return "Hello World!";
	}
}
