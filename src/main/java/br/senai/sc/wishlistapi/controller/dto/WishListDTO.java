package br.senai.sc.wishlistapi.controller.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WishListDTO {
	
	private String title;
	
	private String[] items;
	
	private boolean is_completed;
	
	private UUID user_id;
}
