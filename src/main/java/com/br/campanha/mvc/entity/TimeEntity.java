/** @author diegomauricio
 * 
 * Classe responável por representar o time
 */
package com.br.campanha.mvc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Time")
public class TimeEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	public TimeEntity() {
		super();
	}

	public TimeEntity(Long idTime, String nomeTime) {
		super();
		this.idTime = idTime;
		this.nomeTime = nomeTime;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idTime", unique = true, nullable = false)
	private Long idTime;

	// @JsonIgnore
	@Column(name = "nomeTime")
	// @NotNull(message = "Por Favor, preencha o nome do time .")
	private String nomeTime;

	/**
	 * @return the idTime
	 */
	public Long getIdTime() {
		return idTime;
	}

	/**
	 * @param ididTime
	 *            the id to set
	 */
	public void setIdTime(Long idTime) {
		this.idTime = idTime;
	}

	/**
	 * @return the nomeTime
	 */
	@JsonIgnore
	public String getNomeTime() {
		return nomeTime;
	}

	/**
	 * @param nomeTime
	 *            the nomeTime to set
	 */
	public void setNomeTime(String nomeTime) {
		this.nomeTime = nomeTime;
	}

	/**
	 * Validação de campos vazios
	 * 
	 * @return
	 */
	@JsonIgnore
	public boolean isValidCampanha() {
		if (StringUtils.isEmpty(getNomeTime())) {
			return false;
		}
		return true;
	}

}
