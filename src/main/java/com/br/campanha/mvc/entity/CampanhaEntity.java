/** @author diegomauricio
 * 
 * Classe responável por representar a campanha
 */
package com.br.campanha.mvc.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

@Entity
@Table(name = "CAMPANHA")
public class CampanhaEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	public CampanhaEntity() {
		super();
	}

	public CampanhaEntity(Long id, String nomeCampanha, LocalDate dtInicio, LocalDate dtFim, TimeEntity timeCoracao) {
		super();
		this.id = id;
		this.nomeCampanha = nomeCampanha;
		this.dtInicio = dtInicio;
		this.dtFim = dtFim;
		this.timeCoracao = timeCoracao;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
	private Long id;

	@Column(name = "nomeCampanha", nullable = false)
	@NotNull(message = "Por Favor, preencha o nome da campanha .")
	private String nomeCampanha;

	@Column(name = "dtInicio", nullable = false)
	@NotNull(message = "Por Favor, preencha a data fim.")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt-BR", timezone = "Brazil/East")
	private LocalDate dtInicio;

	@Column(name = "dtFim", nullable = false)
	@NotNull(message = "Por Favor, preencha a data final.")
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt-BR", timezone = "Brazil/East")
	private LocalDate dtFim;

	@ManyToOne(cascade = { CascadeType.ALL })
	private TimeEntity timeCoracao;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the nomeCampanha
	 */
	public String getNomeCampanha() {
		return nomeCampanha;
	}

	/**
	 * @param nomeCampanha
	 *            the nomeCampanha to set
	 */
	public void setNomeCampanha(String nomeCampanha) {
		this.nomeCampanha = nomeCampanha;
	}

	/**
	 * @return the dtInicio
	 */
	public LocalDate getDtInicio() {
		return dtInicio;
	}

	/**
	 * @param dtIncio
	 *            the dtInicio to set
	 */
	public void setDtInicio(LocalDate dtInicio) {
		this.dtInicio = dtInicio;
	}

	/**
	 * @return the dtFim
	 */
	public LocalDate getDtFim() {
		return dtFim;
	}

	/**
	 * @param dtFim
	 *            the dtFim to set
	 */
	public void setDtFim(LocalDate dtFim) {
		this.dtFim = dtFim;
	}

	/**
	 * @return the timeCoracao
	 */
	public TimeEntity getTimeCoracao() {
		return timeCoracao;
	}

	/**
	 * @param timeCoracao
	 *            the timeCoracao to set
	 */
	public void setTimeCoracao(TimeEntity timeCoracao) {
		this.timeCoracao = timeCoracao;
	}

	/**
	 * Validação de campos vazios e se a data inicial é maior do que a final
	 * 
	 * @return
	 */
	@JsonIgnore
	public boolean isValidCampanha() {
		if (StringUtils.isEmpty(getNomeCampanha()) || StringUtils.isEmpty(getDtInicio())
				|| StringUtils.isEmpty(getDtFim()) || getDtInicio().isAfter(getDtFim())) {
			return false;
		}
		return true;
	}

}
