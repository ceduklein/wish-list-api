package br.senai.sc.wishlistapi.controller;

import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.senai.sc.wishlistapi.controller.dto.WishListDTO;
import br.senai.sc.wishlistapi.model.entity.WishList;
import br.senai.sc.wishlistapi.service.WishListService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/lists")
@Tag(name = "Lists")
public class WishListController {

	@Autowired 
	private WishListService service;
	
	@PostMapping
	@Operation(summary = "Create List")
	public ResponseEntity<?> createList(@RequestBody WishListDTO wl) {
		try {
			WishList savedList =  service.save(wl);
			return new ResponseEntity<>(savedList, HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage()); 
		}
	}
	
	@PutMapping("{id}")
	@Operation(summary = "Update List")
	public ResponseEntity<?> createList(@PathVariable("id") UUID id, @RequestBody WishListDTO wl) {
		try {
			WishList updatedList =  service.update(wl, id);
			return new ResponseEntity<>(updatedList, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage()); 
		}
	}
	
	@PatchMapping("/change-status/{id}")
	@Operation(summary = "Change List Status")
	public ResponseEntity<?> changeStatus(@PathVariable("id") UUID id) {
		try {
			WishList updatedList =  service.changeStatus(id);
			return new ResponseEntity<>(updatedList, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage()); 
		}
	}
	
	@GetMapping("/all")
	@Operation(summary = "Get All Lists")
	public ResponseEntity<?> getLists() {
		try {
			List<WishList> lists =  service.findAll();
			return new ResponseEntity<>(lists, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage()); 
		}
	}
	
	@GetMapping("{id}")
	@Operation(summary = "Get List By Id")
	public ResponseEntity<?> getListById(@PathVariable("id") UUID id) {
		try {
			WishList list =  service.findById(id);
			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage()); 
		}
	}
	
	@GetMapping()
	@Operation(summary = "Get List By User")
	public ResponseEntity<?> getListsByUser(@RequestParam UUID user_id) {
		try {
			List<WishList> lists =  service.getListsByUser(user_id);
			return new ResponseEntity<>(lists, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage()); 
		}
	}
	
	@DeleteMapping("{id}")
	@Operation(summary = "Delete List")
	public ResponseEntity<?> deleteList(@PathVariable UUID id) {
		try {
			service.delete(id);
			return new ResponseEntity<>("Successfully deleted list.", HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage()); 
		}
	}
}
