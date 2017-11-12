/** @author diegomauricio
 * 
 * Classe responável por representar o time
 */
package com.br.campanha.mvc.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "TIME")
public class TimeEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	public TimeEntity() {
		super();
	}

	public TimeEntity(String nomeTime) {
		super();
		this.nomeTime = nomeTime;
	}

	/**
	 * @param idTime
	 * @param nomeTime
	 * @param listaCmapanhas
	 */
	public TimeEntity(Long idTime, String nomeTime, List<CampanhaEntity> listaCmapanhas) {
		super();
		this.idTime = idTime;
		this.nomeTime = nomeTime;
		this.listaCmapanhas = listaCmapanhas;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idTime", unique = true, nullable = false)
	private Long idTime;

	// @JsonIgnore
	@Column(name = "nomeTime", unique = true, nullable = false)
	@NotNull(message = "Por Favor, preencha o nome do time.")
	private String nomeTime;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "timeCoracao", cascade = CascadeType.DETACH)
	private List<CampanhaEntity> listaCmapanhas;

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
	 * @return the listaCmapanhas
	 */
	public List<CampanhaEntity> getListaCmapanhas() {
		return listaCmapanhas;
	}

	/**
	 * @param listaCmapanhas
	 *            the listaCmapanhas to set
	 */
	public void setListaCmapanhas(List<CampanhaEntity> listaCmapanhas) {
		this.listaCmapanhas = listaCmapanhas;
	}

	/**
	 * Validação de campos vazios
	 * 
	 * @return
	 */
	@JsonIgnore
	public boolean isInvalidTime() {
		if (StringUtils.isEmpty(getNomeTime())) {
			return true;
		}
		return false;
	}

}
