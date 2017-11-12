package com.br.campanha.service.impl;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.campanha.mvc.entity.TimeEntity;
import com.br.campanha.mvc.repository.TimeRepository;
import com.br.campanha.service.TimeService;

@Service
public class TimeServiceImpl implements TimeService {

	@Autowired
	private TimeRepository timeRepository;

	/**
	 * Serviço exposto para pesquisar um time por nome.
	 *
	 * @return time
	 */
	@Override
	public TimeEntity pesquisarPorNome(final String nome) {
		Logger.getLogger("application").info("Pesquisando o time por nome no Serviço.");
		return this.timeRepository.pesquisarPorNome(nome);
	}

}
