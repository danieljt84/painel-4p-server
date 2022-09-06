package com.controller.dto.security;

public class TokenDTO {
	private String token;
	private String tipo;
	private UserDTO user;

	public TokenDTO(String token, String tipo, UserDTO user) {
		this.token = token;
		this.tipo = tipo;
		this.user = user;
	}
	public String getToken() {
		return token;
	}

	public String getTipo() {
		return tipo;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}
	
	
}
