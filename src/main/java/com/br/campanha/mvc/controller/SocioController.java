/**
 * Classe controller da socio todos os metodos insert, delete, update e  get tem suas chamas nessa classe
 */
package com.br.campanha.mvc.controller;

import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.br.campanha.exception.CampanhaInvalidaException;
import com.br.campanha.exception.CampanhaNotFoundException;
import com.br.campanha.exception.DataInvalidaException;
import com.br.campanha.mvc.dto.SocioResponseDTO;
import com.br.campanha.mvc.entity.CampanhaEntity;
import com.br.campanha.mvc.entity.SocioEntity;
import com.br.campanha.service.SocioService;
import com.br.campanha.util.ConverterUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RequestMapping("/socio")
@Api(value = "socio", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
public class SocioController {

	@Autowired
	private SocioService socioService;

	/**
	 * Chamada rest para inserir a socio.
	 *
	 * @return socio inserido
	 */
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Criar um novo socio", notes = "Criar novos socio")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso", response = CampanhaEntity.class),
			@ApiResponse(code = 201, message = "Recurso Criado", response = CampanhaEntity.class),
			@ApiResponse(code = 204, message = "Não encontrado", response = CampanhaNotFoundException.class),
			@ApiResponse(code = 400, message = "Erro de validação nos campos"),
			@ApiResponse(code = 404, message = "Não encontrado"),
			@ApiResponse(code = 417, message = "Data invalda", response = DataInvalidaException.class),
			@ApiResponse(code = 500, message = "Socio invalido", response = CampanhaInvalidaException.class), })

	@PostMapping(path = "/inserirSocio")
	public @ResponseBody SocioResponseDTO inserirSocio(@Valid @RequestBody(required = true) SocioEntity socioEntity) {
		Logger.getLogger("application").info("Inserindo socio no Controller.");
		return ConverterUtil.converterSocioEntityToResponseDTO(this.socioService.inserir(socioEntity));
	}

}
