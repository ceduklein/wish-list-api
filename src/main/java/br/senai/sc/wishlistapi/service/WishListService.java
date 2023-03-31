package br.senai.sc.wishlistapi.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.senai.sc.wishlistapi.controller.dto.WishListDTO;
import br.senai.sc.wishlistapi.exception.RulesException;
import br.senai.sc.wishlistapi.model.entity.User;
import br.senai.sc.wishlistapi.model.entity.WishList;
import br.senai.sc.wishlistapi.model.repository.UserRepository;
import br.senai.sc.wishlistapi.model.repository.WishListRepository;

@Service
public class WishListService {

	@Autowired
	private WishListRepository repository;
	@Autowired
	private UserRepository userRepository;
	
	public WishList save(WishListDTO wl) throws RulesException {
		Optional<User> user = userRepository.findById(wl.getUser_id());
		if (!user.isPresent()) {
			throw new RulesException("User not found.");
		}
		
		if (!validateTitle(wl.getTitle())) {
			throw new RulesException("Invalid title.");
		}
		
		if (!validateItems(wl.getItems())) {
			throw new RulesException("The list must contain at least one item.");
		}
		
		WishList savedList = new WishList();
		savedList.setTitle(wl.getTitle());
		savedList.setItems(wl.getItems());
		savedList.set_completed(false);
		savedList.setUser(user.get());
		
		return repository.save(savedList);
	}
	
	public WishList update(WishListDTO wl, UUID id) throws RulesException {
		Optional<User> user = userRepository.findById(wl.getUser_id());
		if (!user.isPresent()) {
			throw new RulesException("User not found.");
		}
		
		Optional<WishList> updatedList = repository.findById(id);
		if (!updatedList.isPresent()) {
			throw new RulesException("List not found.");
		}
		
		if (!validateTitle(wl.getTitle())) {
			throw new RulesException("Invalid title.");
		}
		
		if (!validateItems(wl.getItems())) {
			throw new RulesException("The list must contain at least one item.");
		}
		
		updatedList.get().setTitle(wl.getTitle());
		updatedList.get().setItems(wl.getItems());
		
		return repository.save(updatedList.get());
	}
	
	public WishList findById(UUID id) throws RulesException {
		Optional<WishList> list = repository.findById(id);
		if (!list.isPresent()) {
			throw new RulesException("List not found.");
		}
		
		return list.get();
	}
	
	public List<WishList> getListsByUser(UUID user_id) throws RulesException {
		Optional<User> user = userRepository.findById(user_id);
		if(!user.isPresent()) {
			throw new RulesException("User not found.");
		}
		
		List<WishList> lists = repository.findByUser(user.get());
		if(lists.isEmpty()) {
			throw new RulesException("No list were found for this user.");
		}
		
		return lists;
	}
	
	public WishList changeStatus(UUID id) throws RulesException {
		Optional<WishList> list = repository.findById(id);
		if (!list.isPresent()) {
			throw new RulesException("List not found.");
		}
		
		list.get().set_completed(!list.get().is_completed());
		return repository.save(list.get());
	}
	
	public void delete(UUID id) throws RulesException {
		Optional<WishList> list = repository.findById(id);
		if (!list.isPresent()) {
			throw new RulesException("List not found.");
		}
		
		repository.delete(list.get());
	}
	
	private boolean validateTitle(String title) {
		if (title == null || title.length() < 1)
			return false;
		else
			return true;
	}
	
	private boolean validateItems(String[] items) {
		if (items.length == 0)
			return false;
		else 
			return true;
	}
}
