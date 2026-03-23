package models;

import java.math.BigDecimal;

public class Servico {
	private int idServico;
	private String Nome;
	private BigDecimal valor;
	
	
	public Servico() {

	}


	public int getIdServico() {
		return idServico;
	}


	public String getNome() {
		return Nome;
	}


	public BigDecimal getValor() {
		return valor;
	}


	public void setIdServico(int idServico) {
		this.idServico = idServico;
	}


	public void setNome(String nome) {
		Nome = nome;
	}


	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	
	
	
}

