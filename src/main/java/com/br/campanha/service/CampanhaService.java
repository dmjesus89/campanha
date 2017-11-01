/**
 * @author diegomauricio
 * 
 * Interface do service da campanha
 */
package com.br.campanha.service;

import java.util.List;

import com.br.campanha.exception.CampanhaNotFoundException;
import com.br.campanha.mvc.entity.CampanhaEntity;

public interface CampanhaService {

	CampanhaEntity inserir(CampanhaEntity campanha);

	List<CampanhaEntity> listarTodosVigentes();

	void remover(Long id) throws CampanhaNotFoundException;

	CampanhaEntity alterar(Long id, CampanhaEntity campanha) throws CampanhaNotFoundException;

}
