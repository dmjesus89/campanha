package com.br.campanha.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.EXPECTATION_FAILED, reason = "Preencher todos os campos obrigatórios")
public class CampanhaInvalidaException extends Exception {

	private static final long serialVersionUID = 1L;

}
