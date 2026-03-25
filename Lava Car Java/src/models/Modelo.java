package models;

public class Modelo {
	private int idModelo;
	private int idMarca;
	private String nome;

	public Modelo() {
	}

	public int getIdModelo() {
		return idModelo;
	}

	public String getNome() {
		return nome;
	}

	public void setIdModelo(int idModelo) {
		this.idModelo = idModelo;
	}

	public void setIdMarca(int idMarca) {
		this.idMarca = idMarca;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdMarca() {
		return idMarca;
	}

}
