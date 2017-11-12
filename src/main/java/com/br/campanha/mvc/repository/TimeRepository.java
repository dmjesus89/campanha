/** @author diegomauricio
 * 
 * Classe responsável pela integracão da classe timeEntity com o banco de dados 
 */
package com.br.campanha.mvc.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.br.campanha.mvc.entity.SocioEntity;
import com.br.campanha.mvc.entity.TimeEntity;

@Repository
public interface TimeRepository extends CrudRepository<SocioEntity, Long> {

	@Query("select t from TimeEntity t where UPPER(t.nomeTime) = UPPER(:nomeTime)")
	TimeEntity pesquisarPorNome(final @Param("nomeTime") String nomeTime);

}
