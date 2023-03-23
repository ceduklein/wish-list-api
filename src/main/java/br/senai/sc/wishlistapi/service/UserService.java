package br.senai.sc.wishlistapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public User auth(String email, String password) throws RulesException {
		User user = repository.findByEmail(email);
		if (user == null) {
			throw new RulesException("Incorrect email/password");
		}
		
		if (!user.getPassword().equals(password)) {
			throw new RulesException("Incorrect email/password");
		}
		
		user.setPassword(null);
		return user;
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
