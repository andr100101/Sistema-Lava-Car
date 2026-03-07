package models;

import java.math.BigDecimal;

public class Servico {
	private int idServico;
	private String Nome;
	private BigDecimal valor;
	
	
	public Servico(int idServico, String nome, BigDecimal valor) {
		this.idServico = idServico;
		Nome = nome;
		this.valor = valor;
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
	
	
	
}

