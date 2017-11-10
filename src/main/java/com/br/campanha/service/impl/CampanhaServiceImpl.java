/**
 * @author diegomauricio
 * 
 * Implementação do service da campanha
 */
package com.br.campanha.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.br.campanha.exception.CampanhaInvalidaException;
import com.br.campanha.exception.CampanhaNotFoundException;
import com.br.campanha.exception.DataInvalidaException;
import com.br.campanha.mvc.entity.CampanhaEntity;
import com.br.campanha.mvc.repository.CampanhaRepository;
import com.br.campanha.service.CampanhaService;

@Service
public class CampanhaServiceImpl implements CampanhaService {

	@Autowired
	private CampanhaRepository camapanhaRepository;

	/**
	 * Serviço exposto para inserir as campanhas.
	 *
	 * @return capamanha insredia
	 */
	@Override
	@Transactional(rollbackFor = { Exception.class })
	public CampanhaEntity inserir(CampanhaEntity campanha)
			throws CampanhaInvalidaException, DataInvalidaException {
		Logger.getLogger("application").info("Inserindo campanha do Serviço.");
			if (campanha.isInvalidCampanha() || campanha.getTimeCoracao().isInvalidTime()) {
				throw new CampanhaInvalidaException();
			}
			if (campanha.isInvalidDate()) {
				throw new DataInvalidaException();
			}
			configurarDatasVigencia(campanha);
			camapanhaRepository.save(campanha);
		return campanha;
	}

	/**
	 * Serviço exposto para listar todas campanhas vigentes.
	 *
	 * @return lista de capamanhas vigentes
	 */
	@Override
	public List<CampanhaEntity> listarTodosVigentes() {
		Logger.getLogger("application").info("Listando as campanhas vigentes do Serviço.");
		return this.camapanhaRepository.listarTodosVigentes(LocalDate.now());
	}

	/**
	 * Serviço exposto para alterar um campanha.
	 *
	 * @return capamanha alterada
	 */
	@Override
	public CampanhaEntity alterar(Long id, CampanhaEntity campanha)
			throws CampanhaInvalidaException, CampanhaNotFoundException, DataInvalidaException {
		Logger.getLogger("application").info("Alterando a campanha do Serviço.");
		if (!this.camapanhaRepository.exists(id)) {
			throw new CampanhaNotFoundException();
		}
		if (campanha.isInvalidCampanha() || campanha.getTimeCoracao().isInvalidTime()) {
			throw new CampanhaInvalidaException();
		}
		if (campanha.isInvalidDate()) {
			throw new DataInvalidaException();
		}
		configurarDatasVigencia(campanha);
		campanha.setId(id);
		return this.camapanhaRepository.save(campanha);
	}

	/**
	 * Serviço exposto para remoer um campanha.
	 *
	 * @return capamanha alterada
	 */
	@Override
	public void remover(Long id) throws CampanhaNotFoundException {
		Logger.getLogger("application").info("Removendo a campanha do Serviço.");
		if (!this.camapanhaRepository.exists(id)) {
			throw new CampanhaNotFoundException();
		}
		this.camapanhaRepository.delete(id);
	}

	/**
	 * Metódo que verifica se existe alguma campanha vigente no mesmo periodo
	 * 
	 * @param campanha
	 */
	private void configurarDatasVigencia(CampanhaEntity campanha) {
		List<CampanhaEntity> listaCampanhasSimilares = this.camapanhaRepository
				.listarVigenciasSimilares(campanha.getDtInicio(), campanha.getDtFim());
		if (!listaCampanhasSimilares.isEmpty()) {
			for (int i = 0; i < listaCampanhasSimilares.size(); i++) {
				CampanhaEntity campanhaIt = listaCampanhasSimilares.get(i);
				LocalDate dtFim = campanhaIt.getDtFim().plusDays(1);
				while (dtFim.isEqual(campanha.getDtFim())
						|| isPossuiVigenciaFinalSimilar(listaCampanhasSimilares, dtFim)) {
					dtFim = dtFim.plusDays(1);
				}
				campanhaIt.setDtFim(dtFim);
			}
		}
	}

	/**
	 * Método que verifica se existe na base alguma campanha com a vigencial final
	 * igual a nova campanha
	 * 
	 * @param listaCampanhasVigentesSimilares
	 * @param dtFim
	 *            dataFinal da campanha corrente
	 * @return caso verdadeiro vai somar mais um dia na vigencia final da campanha
	 *         corrente
	 */
	private boolean isPossuiVigenciaFinalSimilar(final List<CampanhaEntity> listaCampanhasVigentesSimilares,
			final LocalDate dtFim) {
		for (CampanhaEntity campanhaEntity : listaCampanhasVigentesSimilares) {
			if (campanhaEntity.getDtFim().equals(dtFim)) {
				return true;
			}
		}
		return false;
	}

}
