/**
 * @author diegomauricio
 * 
 * Implementação do service da campanha
 */
package com.br.campanha.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.campanha.exception.CampanhaNotFoundException;
import com.br.campanha.mvc.entity.CampanhaEntity;
import com.br.campanha.mvc.repository.CamapanhaRepository;
import com.br.campanha.service.CampanhaService;

@Service
public class CampanhaServiceImpl implements CampanhaService {

	@Autowired
	private CamapanhaRepository camapanhaRepository;

	/**
	 * Serviço exposto para inserir as campanhas.
	 *
	 * @return capamanha insredia
	 */
	@Override
	public CampanhaEntity inserir(CampanhaEntity campanha) {
		Logger.getLogger("application").info("Inserindo campanha do Serviço.");
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
		return this.camapanhaRepository.save(campanha);
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
	public CampanhaEntity alterar(Long id, CampanhaEntity address) throws CampanhaNotFoundException {
		Logger.getLogger("application").info("Alterando a campanha do Serviço.");
		if (!this.camapanhaRepository.exists(id)) {
			throw new CampanhaNotFoundException();
		}
		return this.camapanhaRepository.save(address);
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
