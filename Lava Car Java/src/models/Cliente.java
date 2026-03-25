package models;

import java.sql.Date;

public class Cliente {
	private int idCliente;
	private String nome;
	private String whats;
	private Date dataCadastro;

	public Cliente() {
	}

	public int getIdCliente() {
		return idCliente;
	}

	public String getNome() {
		return nome;
	}

	public String getWhats() {
		return whats;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setWhats(String whats) {
		this.whats = whats;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

}
