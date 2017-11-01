/**
 * Classe controller da campanha todos os metodos insert, delete, update e  get tem suas chamas nessa classe
 */
package com.br.campanha.mvc.controller;

import java.util.List;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.br.campanha.exception.CampanhaNotFoundException;
import com.br.campanha.mvc.entity.CampanhaEntity;
import com.br.campanha.service.CampanhaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RequestMapping("/campanha")
@Api(value = "campanha", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
public class CampanhaController {

	@Autowired
	private CampanhaService campanhaService;

	/**
	 * Chamada rest para inserir as campanhas.
	 *
	 * @return capamanha insredia
	 */
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Criar nova campanha", notes = "Criar novas campanhas")
	@ApiResponses(value = { 
			@ApiResponse(code = 400, message = "Erro de validação nos campos"),
			@ApiResponse(code = 404, message = "Não encontrado"),
			@ApiResponse(code = 201, message = "Recurso Criado") })
	@PostMapping(path = "/inserirCampanha")
	public @ResponseBody CampanhaEntity inserirCampanha(
			@Valid @RequestBody(required = true) CampanhaEntity campanhaEntity) {
		Logger.getLogger("application").info("Inserindo campanha do Controller.");
		this.campanhaService.inserir(campanhaEntity);
		return campanhaEntity;
	}

	/**
	 * Chamada rest para listar todas campanhas vigentes.
	 *
	 * @return lista de capamanhas vigentes
	 */
	@ApiOperation(value = "Lista de campanhas vigentes", notes = "Returna todas as campanhas vigentes")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Sucesso", response = CampanhaEntity.class),
			@ApiResponse(code = 500, message = "Falha") })
	@GetMapping(path = "/listaCampanhasVigente")
	public @ResponseBody List<CampanhaEntity> listaCampanhasVigente() {
		Logger.getLogger("application").info("Listando as campanhas vigentes do Controller.");
		List<CampanhaEntity> listaCampanhas = this.campanhaService.listarTodosVigentes();
		return listaCampanhas;
	}

	/**
	 * Chamada rest alterar um campanha.
	 *
	 * @return capamanha alterada
	 */
	@ApiOperation(value = "Alterar uma campanha existente", notes = "Alterar uma camapnha existente por ID")
	@ApiResponses(value = { 
			@ApiResponse(code = 400, message = "Erro de validação nos campos"),
			@ApiResponse(code = 404, message = "Não encontrado", response = CampanhaNotFoundException.class),
			@ApiResponse(code = 201, message = "Recurso criado") })
	@PutMapping(value = "/alterarCampanha/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody CampanhaEntity alterarCampanha(@PathVariable("id") final Long id,
			@Valid @RequestBody(required = true) CampanhaEntity campanhaEntity) throws CampanhaNotFoundException {
		Logger.getLogger("application").info("Alterando a campanha do Controller.");
		return this.campanhaService.alterar(id, campanhaEntity);
	}

	/**
	 * Chamada rest deletar um campanha.
	 *
	 */
	@ApiOperation(value = "Deleta uma campanha", notes = "Retorna a campanha por Id")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "ID do retorno da campanha", required = true, dataType = "Long", paramType = "path", defaultValue = "1") })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Succeso", response = CampanhaEntity.class),
			@ApiResponse(code = 404, message = "Não encontrado", response = CampanhaNotFoundException.class),
			@ApiResponse(code = 500, message = "Falha") })
	@DeleteMapping(value = "/removerCampanha/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void removerCampanha(@PathVariable("id") final Long id) throws CampanhaNotFoundException {
		Logger.getLogger("application").info("Removendo a campanha do Controller.");
		this.campanhaService.remover(id);
	}

}
