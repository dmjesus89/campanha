/**
 * @author diegomauricio
 * 
 * Interface do service da campanha
 */
package com.br.campanha.service;

import java.util.List;

import com.br.campanha.exception.CampanhaInvalidaException;
import com.br.campanha.exception.CampanhaNotFoundException;
import com.br.campanha.exception.DataInvalidaException;
import com.br.campanha.mvc.entity.CampanhaEntity;

public interface CampanhaService {

	CampanhaEntity inserir(CampanhaEntity campanha) throws CampanhaInvalidaException, DataInvalidaException;

	List<CampanhaEntity> listarTodosVigentes();

	void remover(Long id) throws CampanhaNotFoundException;

	CampanhaEntity alterar(Long id, CampanhaEntity campanha)
			throws CampanhaInvalidaException, CampanhaNotFoundException, DataInvalidaException;

	List<CampanhaEntity> listarTodosVigentesPorTime(final String nomeTime);

}
