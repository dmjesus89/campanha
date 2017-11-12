/**
 * @author diegomauricio
 * 
 * Classe response para o socio do time
 */
package com.br.campanha.mvc.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SocioResponseDTO {

	private String nomeCompleto;
	private String email;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt-BR", timezone = "Brazil/East")
	private LocalDate dtNascimento;
	private String timeCoracao;

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
	public String getTimeCoracao() {
		return timeCoracao;
	}

	/**
	 * @param timeCoracao
	 *            the timeCoracao to set
	 */
	public void setTimeCoracao(String timeCoracao) {
		this.timeCoracao = timeCoracao;
	}

}
