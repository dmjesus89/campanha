/** @author diegomauricio
 * 
 * Classe responsável pela integracão da classe CampanhaEntity com o banco de dados 
 */
package com.br.campanha.mvc.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.br.campanha.mvc.entity.CampanhaEntity;

@Repository
public interface CampanhaRepository extends CrudRepository<CampanhaEntity, Long> {

	/**
	 * Método responsável por filtrar apenas as campanhas vigentes, considerando
	 * data de incio e fim
	 * 
	 * @return lista de todas as campanhas vigentes
	 */
	@Query("SELECT CE FROM CampanhaEntity CE  WHERE ? BETWEEN CE.dtInicio AND CE.dtFim ")
	List<CampanhaEntity> listarTodosVigentes(final LocalDate currentDate);

	/**
	 * Método responsável por filtrar campanhas com mesma vigencia
	 * 
	 * @return campanha vigente
	 */
	@Query("SELECT CE FROM CampanhaEntity CE  WHERE ?1 >= CE.dtInicio AND ?2 >=  CE.dtFim AND ?1 BETWEEN CE.dtInicio AND CE.dtFim  ORDER BY CE.dtFim ")
	List<CampanhaEntity> listarVigenciasSimilares(final LocalDate dtInicio, final LocalDate dtFim);

	/**
	 * Método responsável por filtrar campanhas com mesma fim de vigencia
	 * 
	 * @return campanha vigente
	 */
	@Query("SELECT CE FROM CampanhaEntity CE  WHERE ?1 >= CE.dtInicio AND ?2 >=  CE.dtFim AND ?1 = CE.dtFim  ORDER BY CE.dtFim ")
	List<CampanhaEntity> listarFimVigenciasSimilares(final LocalDate dtInicio, final LocalDate dtFim);

	/**
	 * Método responsável por filtrar apenas as campanhas vigentes, considerando
	 * data de incio e fim e o time de coração
	 * 
	 * @return lista de todas as campanhas vigentes
	 */
	@Query("SELECT CE FROM CampanhaEntity CE INNER JOIN CE.timeCoracao tc on tc.nomeTime = :nomeTime WHERE :currentDate BETWEEN CE.dtInicio AND CE.dtFim ")
	List<CampanhaEntity> listarTodosVigentesPorTime(final LocalDate currentDate, final String nomeTime);

}
