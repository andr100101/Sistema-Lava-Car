package models;

public class Modelo {
	private int idModelo;
	private int idMarca;
	private String nome;
	
	public Modelo(int idModelo,String nome, int idMarca) {
		this.idModelo = idModelo;
		this.nome = nome;
		this.idMarca = idMarca;
	}
	
	public int getIdModelo() {
        return idModelo;
    }
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdMarca() {
		return idMarca;
	}
	
	
}
