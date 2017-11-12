/**
 * @author diegomauricio
 * 
 * Classe responsavel por converter os objetos 
 */
package com.br.campanha.util;

import java.util.ArrayList;
import java.util.List;

import com.br.campanha.mvc.dto.CampanhaResponseDTO;
import com.br.campanha.mvc.dto.SocioResponseDTO;
import com.br.campanha.mvc.entity.CampanhaEntity;
import com.br.campanha.mvc.entity.SocioEntity;

public class ConverterUtil {

	/**
	 * Converter o objeto campanha entity para campanha response dto
	 * 
	 * @param campanha
	 * @return CampanhaResponseDTO
	 */
	public static CampanhaResponseDTO converterCampanhaEntityToResponseDTO(CampanhaEntity campanha) {
		CampanhaResponseDTO campanhaResponseDTO = new CampanhaResponseDTO();
		campanhaResponseDTO.setNomeCampanha(campanha.getNomeCampanha());
		campanhaResponseDTO.setDtFim(campanha.getDtFim());
		campanhaResponseDTO.setDtInicio(campanha.getDtInicio());
		campanhaResponseDTO.setIdTimeCoracao(campanha.getTimeCoracao().getIdTime());
		return campanhaResponseDTO;
	}

	/**
	 * Converter a lista de o objeto campanha entity para lista campanha response
	 * dto
	 * 
	 * @param listaCampanhaEntity
	 * @return
	 */
	public static List<CampanhaResponseDTO> converterListaCampanhaEntityToResponseDTO(
			List<CampanhaEntity> listaCampanhaEntity) {
		List<CampanhaResponseDTO> listaCampanhaDTO = new ArrayList<>();
		for (CampanhaEntity campanha : listaCampanhaEntity) {
			listaCampanhaDTO.add(converterCampanhaEntityToResponseDTO(campanha));
		}
		return listaCampanhaDTO;
	}

	/**
	 * Converter o objeto socio entity para socio response
	 * 
	 * @param socioEntity
	 * @return new SocioResponseDTO
	 */
	public static SocioResponseDTO converterSocioEntityToResponseDTO(SocioEntity socioEntity) {
		SocioResponseDTO socioResponseDTO = new SocioResponseDTO();
		socioResponseDTO.setNomeCompleto(socioEntity.getNomeCompleto());
		socioResponseDTO.setEmail(socioEntity.getEmail());
		socioResponseDTO.setDtNascimento(socioEntity.getDtNascimento());
		socioResponseDTO.setTimeCoracao(socioEntity.getTimeCoracao().getNomeTime());
		return socioResponseDTO;
	}
}
