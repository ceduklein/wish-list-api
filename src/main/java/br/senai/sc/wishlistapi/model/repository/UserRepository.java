package br.senai.sc.wishlistapi.model.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.senai.sc.wishlistapi.model.entity.User;

public interface UserRepository extends JpaRepository<User, UUID> {
	
	Optional<User> findByEmail(String email);
	
	boolean existsByEmail(String email);
	
}
