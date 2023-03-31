package br.senai.sc.wishlistapi.model.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.senai.sc.wishlistapi.model.entity.User;
import br.senai.sc.wishlistapi.model.entity.WishList;

public interface WishListRepository extends JpaRepository<WishList, UUID> {

	List<WishList> findByUser(User user);
	
}
