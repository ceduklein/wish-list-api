package br.senai.sc.wishlistapi.controller.dto;

import lombok.Data;

@Data
public class PasswordDTO {

	private String oldPassword;
	private String newPassword;
	private String passwordConfirmation;
}
