package br.senai.sc.wishlistapi.controller.dto;

import lombok.Data;

@Data
public class AuthRequestDTO {

	private String email;
	private String password;
	
}
