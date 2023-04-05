package br.senai.sc.wishlistapi.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.senai.sc.wishlistapi.controller.dto.PasswordDTO;
import br.senai.sc.wishlistapi.controller.dto.UserDTO;
import br.senai.sc.wishlistapi.exception.RulesException;
import br.senai.sc.wishlistapi.model.entity.User;
import br.senai.sc.wishlistapi.model.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public User save(UserDTO user) throws RulesException {
		if (!validateName(user.getName())) {
			throw new RulesException("Invalid name.");
		}

		if (validateEmail(user.getEmail())) {
			throw new RulesException("Email already registered.");
		}

		if (!validatePassword(user.getPassword())) {
			throw new RulesException("Password must have at least 4 characters.");
		}
		
		User savedUser = new User();
		savedUser.setName(user.getName());
		savedUser.setBirthdate(user.getBirthdate());
		savedUser.setEmail(user.getEmail());
		savedUser.setPassword(user.getPassword());

		return repository.save(savedUser);
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
	
	public List<User> findAll() throws RulesException {
		List<User> users = repository.findAll();
		if (users.isEmpty()) {
			throw new RulesException("No users were found.");
		}
		return users;
	}
	
	public Optional<User> findById(UUID id) throws RulesException {
		Optional<User> user = repository.findById(id);
		if (!user.isPresent()) {
			throw new RulesException("User not found.");
		}
		
		return user;
	}
	
	public User update(UUID id, UserDTO u) throws RulesException {
		Optional<User> user = repository.findById(id);
		if (!user.isPresent()) {
			throw new RulesException("User not found.");
		}
		
		if (!validateName(u.getName())) {
			throw new RulesException("Invalid name.");
		}

		user.get().setName(u.getName());
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
