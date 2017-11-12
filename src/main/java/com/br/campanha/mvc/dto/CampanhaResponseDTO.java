package com.br.campanha.mvc.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CampanhaResponseDTO {

	private String nomeCampanha;
	private Long idTimeCoracao;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt-BR", timezone = "Brazil/East")
	private LocalDate dtInicio;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt-BR", timezone = "Brazil/East")
	private LocalDate dtFim;

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
	 * @return the idTimeCoracao
	 */
	public Long getIdTimeCoracao() {
		return idTimeCoracao;
	}

	/**
	 * @param idTimeCoracao
	 *            the idTimeCoracao to set
	 */
	public void setIdTimeCoracao(Long idTimeCoracao) {
		this.idTimeCoracao = idTimeCoracao;
	}

	/**
	 * @return the dtInicio
	 */
	public LocalDate getDtInicio() {
		return dtInicio;
	}

	/**
	 * @param dtInicio
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

}
