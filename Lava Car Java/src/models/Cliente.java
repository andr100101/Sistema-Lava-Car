package models;

public class Cliente {
	private int idCliente;
	private String nome;
	private String whats;
	
	public Cliente(int idCliente, String nome, String whats) {
		this.idCliente = idCliente;
		this.nome = nome;
		this.whats = whats;
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
	
	
}
