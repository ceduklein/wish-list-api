package br.senai.sc.wishlistapi.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.senai.sc.wishlistapi.controller.dto.PasswordDTO;
import br.senai.sc.wishlistapi.exception.RulesException;
import br.senai.sc.wishlistapi.model.entity.User;
import br.senai.sc.wishlistapi.model.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public User save(User user) throws RulesException {
		if (!validateName(user.getName())) {
			throw new RulesException("Invalid name.");
		}

		if (validateEmail(user.getEmail())) {
			throw new RulesException("Email already registered.");
		}

		if (!validatePassword(user.getPassword())) {
			throw new RulesException("Password must have at least 4 characters.");
		}

		return repository.save(user);
	}

	public Optional<User> auth(String email, String password) throws RulesException {
		Optional<User> user = repository.findByEmail(email);
		if (!user.isPresent()) {
			throw new RulesException("Incorrect email/password");
		}
		
		if (!user.get().getPassword().equals(password)) {
			throw new RulesException("Incorrect email/password");
		}
		
		return user;
	}
	
	public Optional<User> findById(UUID id) throws RulesException {
		Optional<User> user = repository.findById(id);
		if (!user.isPresent()) {
			throw new RulesException("User not found.");
		}
		
		return user;
	}
	
	public User update(UUID id, User u) throws RulesException {
		Optional<User> user = repository.findById(id);
		if (!user.isPresent()) {
			throw new RulesException("User not found.");
		}
		
		if (!validateName(u.getName())) {
			throw new RulesException("Invalid name.");
		}

		if (validateEmail(u.getEmail())) {
			throw new RulesException("Email already registered.");
		}
		
		user.get().setName(u.getName());
		user.get().setEmail(u.getEmail());
		user.get().setBirthdate(u.getBirthdate());
		return repository.save(user.get());
	}
	
	public void changePassword(UUID id, PasswordDTO dto) throws RulesException {
		Optional<User> user = repository.findById(id);
		if (!user.isPresent()) {
			throw new RulesException("User not found.");
		}
		
		if (!user.get().getPassword().equals(dto.getOldPassword())) {
			throw new RulesException("Old password is incorrect.");
		}
		
		if (!dto.getNewPassword().equals(dto.getPasswordConfirmation())) {
			throw new RulesException("The new password and its confirmation do not match.");
		}
			
		user.get().setPassword(dto.getNewPassword());
		repository.save(user.get());
	}
	
	public void delete(UUID id) throws RulesException {
		Optional<User> user = repository.findById(id);
		if (!user.isPresent()) {
			throw new RulesException("User not found.");
		}
		
		repository.delete(user.get());
	}

	private boolean validateName(String name) {
		if (name == null || name.length() < 3)
			return false;
		else
			return true;
	}

	private boolean validateEmail(String email) {
		return repository.existsByEmail(email);
	}

	private boolean validatePassword(String password) {
		if (password == null || password.length() < 4)
			return false;
		else
			return true;
	}

}
