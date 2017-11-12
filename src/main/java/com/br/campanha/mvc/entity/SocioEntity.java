/** @author diegomauricio
 * 
 * Classe responável por representar o socio do time
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

@Entity
@Table(name = "SOCIO")
public class SocioEntity implements Serializable {

	private static final long serialVersionUID = 2L;

	public SocioEntity() {
		super();
	}

	public SocioEntity(Long id) {
		super();
		this.id = id;
	}

	/**
	 * @param id
	 * @param nomeCompleto
	 * @param email
	 * @param dtNascimento
	 * @param timeCoracao
	 * @param stsAtivo
	 */
	public SocioEntity(Long id, String nomeCompleto, String email, LocalDate dtNascimento, TimeEntity timeCoracao,
			Boolean stsAtivo) {
		super();
		this.id = id;
		this.nomeCompleto = nomeCompleto;
		this.email = email;
		this.dtNascimento = dtNascimento;
		this.timeCoracao = timeCoracao;
		this.stsAtivo = stsAtivo;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
	private Long id;

	@Column(name = "nomeCompleto", nullable = false)
	@NotNull(message = "Por Favor, preencha o nome do socio .")
	private String nomeCompleto;

	@Column(name = "email", nullable = false)
	@NotNull(message = "Por Favor, preencha o email.")
	private String email;

	@Column(name = "dtNascimento", nullable = false)
	@NotNull(message = "Por Favor, preencha a data de nascimento.")
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt-BR", timezone = "Brazil/East")
	private LocalDate dtNascimento;

	@OneToOne(cascade = { CascadeType.ALL })
	private TimeEntity timeCoracao;

	@Column(name = "stsAtivo",  columnDefinition="tinyint(1) default 1")
	private boolean stsAtivo ;

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
	 * @return the nomeCompleto
	 */
	public String getNomeCompleto() {
		return nomeCompleto;
	}

	/**
	 * @param nomeCompleto
	 *            the nomeCompleto to set
	 */
	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the dtNascimento
	 */
	public LocalDate getDtNascimento() {
		return dtNascimento;
	}

	/**
	 * @param dtNascimento
	 *            the dtNascimento to set
	 */
	public void setDtNascimento(LocalDate dtNascimento) {
		this.dtNascimento = dtNascimento;
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
	 * @return the stsAtivo
	 */
	public boolean getStsAtivo() {
		return stsAtivo;
	}

	/**
	 * @param stsAtivo
	 *            the stsAtivo to set
	 */
	public void setStsAtivo(boolean stsAtivo) {
		this.stsAtivo = stsAtivo;
	}

}
