package br.senai.sc.wishlistapi.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.senai.sc.wishlistapi.model.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
	
	User findByEmail(String email);
	
	boolean existsByEmail(String email);
	
}
