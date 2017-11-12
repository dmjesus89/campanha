/** @author diegomauricio
 * 
 * Classe responsável pela integracão da classe socioEntity com o banco de dados 
 */
package com.br.campanha.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.br.campanha.mvc.entity.SocioEntity;

@Repository
public interface SocioRepository extends JpaRepository<SocioEntity, Long> {

	@Query("select s from SocioEntity s where s.stsAtivo = 1 and s.email= :email")
	SocioEntity pesquisarSocioPorEmail(final @Param("email") String email);

}
