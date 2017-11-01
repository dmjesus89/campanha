package com.br.campanha.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Campanha inválida")
public class CampanhaNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

}
