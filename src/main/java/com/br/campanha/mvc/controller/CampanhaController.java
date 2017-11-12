/**
 * Classe controller da campanha todos os metodos insert, delete, update e  get tem suas chamas nessa classe
 */
package com.br.campanha.mvc.controller;

import java.util.List;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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

import com.br.campanha.exception.CampanhaInvalidaException;
import com.br.campanha.exception.CampanhaNotFoundException;
import com.br.campanha.exception.DataInvalidaException;
import com.br.campanha.mvc.dto.CampanhaResponseDTO;
import com.br.campanha.mvc.entity.CampanhaEntity;
import com.br.campanha.service.CampanhaService;
import com.br.campanha.util.ConverterUtil;

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
	 * Chamada rest para inserir a campanha.
	 *
	 * @return capamanha insredia
	 */
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Criar nova campanha", notes = "Criar novas campanhas")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso", response = CampanhaEntity.class),
			@ApiResponse(code = 201, message = "Recurso Criado", response = CampanhaEntity.class),
			@ApiResponse(code = 204, message = "Não encontrado", response = CampanhaNotFoundException.class),
			@ApiResponse(code = 400, message = "Erro de validação nos campos"),
			@ApiResponse(code = 404, message = "Não encontrado"),
			@ApiResponse(code = 417, message = "Data invalda", response = DataInvalidaException.class),
			@ApiResponse(code = 500, message = "Campanha invalida", response = CampanhaInvalidaException.class), })

	@PostMapping(path = "/inserirCampanha")
	@CacheEvict(allEntries = true, value = "listaCampanhasVigente", beforeInvocation = false)
	public @ResponseBody CampanhaResponseDTO inserirCampanha(
			@Valid @RequestBody(required = true) CampanhaEntity campanhaEntity)
			throws CampanhaInvalidaException, DataInvalidaException {
		Logger.getLogger("application").info("Inserindo campanha do Controller.");
		this.campanhaService.inserir(campanhaEntity);
		return ConverterUtil.converterCampanhaEntityToResponseDTO(campanhaEntity);
	}

	/**
	 * Chamada rest para listar todas campanhas vigentes.
	 *
	 * @return lista de capamanhas vigentes
	 */
	@ApiOperation(value = "Lista de campanhas vigentes", notes = "Returna todas as campanhas vigentes")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Falha") })
	@GetMapping(path = "/listaCampanhasVigente")
	@Cacheable(value = "listaCampanhasVigente")
	public @ResponseBody List<CampanhaResponseDTO> listaCampanhasVigente() {
		Logger.getLogger("application").info("Listando as campanhas vigentes do Controller.");
		List<CampanhaEntity> listaCampanhas = this.campanhaService.listarTodosVigentes();
		return ConverterUtil.converterListaCampanhaEntityToResponseDTO(listaCampanhas);
	}

	/**
	 * Chamada rest alterar um campanha.
	 *
	 * @return campanha alterada
	 */
	@ApiOperation(value = "Alterar uma campanha existente", notes = "Alterar uma camapnha existente por ID")
	@ApiResponses(value = {
			@ApiResponse(code = 204, message = "Não encontrado", response = CampanhaNotFoundException.class),
			@ApiResponse(code = 201, message = "Recurso criado"),
			@ApiResponse(code = 400, message = "Erro de validação nos campos", response = CampanhaInvalidaException.class),
			@ApiResponse(code = 404, message = "Não encontrado", response = CampanhaNotFoundException.class) })
	@PutMapping(value = "/alterarCampanha/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@CacheEvict(allEntries = true, value = "listaCampanhasVigente", beforeInvocation = false)
	public @ResponseBody CampanhaResponseDTO alterarCampanha(@PathVariable("id") final Long id,
			@Valid @RequestBody(required = true) CampanhaEntity campanhaEntity)
			throws CampanhaInvalidaException, CampanhaNotFoundException, DataInvalidaException {
		Logger.getLogger("application").info("Alterando a campanha do Controller.");
		this.campanhaService.alterar(id, campanhaEntity);
		return ConverterUtil.converterCampanhaEntityToResponseDTO(campanhaEntity);
	}

	/**
	 * Chamada rest deletar um campanha.
	 *
	 */
	@ApiOperation(value = "Deleta uma campanha", notes = "Retorna a campanha por Id")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "ID do retorno da campanha", required = true, dataType = "Long", paramType = "path", defaultValue = "1") })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Succeso", response = CampanhaEntity.class),
			@ApiResponse(code = 204, message = "Não encontrado", response = CampanhaNotFoundException.class),
			@ApiResponse(code = 404, message = "Não encontrado", response = CampanhaNotFoundException.class),
			@ApiResponse(code = 500, message = "Falha") })
	@DeleteMapping(value = "/removerCampanha/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@CacheEvict(allEntries = true, value = "listaCampanhasVigente", beforeInvocation = false)
	public void removerCampanha(@PathVariable("id") final Long id) throws CampanhaNotFoundException {
		Logger.getLogger("application").info("Removendo a campanha do Controller.");
		this.campanhaService.remover(id);
	}

}
