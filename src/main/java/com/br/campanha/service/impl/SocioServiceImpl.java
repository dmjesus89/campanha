/**
 * @author diegomauricio
 * 
 * Implementação do service do socio
 */
package com.br.campanha.service.impl;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.br.campanha.exception.CampanhaInvalidaException;
import com.br.campanha.exception.DataInvalidaException;
import com.br.campanha.mvc.entity.SocioEntity;
import com.br.campanha.mvc.repository.SocioRepository;
import com.br.campanha.service.SocioService;

@Service
public class SocioServiceImpl implements SocioService {

	@Autowired
	private SocioRepository socioRepository;

	/**
	 * Serviço exposto para inserir o socio.
	 *
	 * @return socio inserido
	 */
	@Override
	@Transactional(rollbackFor = { Exception.class })
	public SocioEntity inserir(SocioEntity socio) {
		Logger.getLogger("application").info("Inserindo  socio no Service.");
		if(!isExisteSocioComEsseEmail(socio.getEmail())) {
			socioRepository.save(socio);
		}
		return socio;
	}

	/**
	 * Pesquisar se existe já um socio ativo com esse email
	 * 
	 * @param email
	 * @return
	 * @throws CampanhaInvalidaException
	 * @throws DataInvalidaException
	 */
	private boolean isExisteSocioComEsseEmail(String email) {
		Logger.getLogger("application").info("Pesquisar socio por email no Service.");
		if (this.socioRepository.pesquisarSocioPorEmail(email) != null)
			return true;
		return false;
	}

}
