package models;

public class Carro {
	private int idCarro;
	private int idModelo;
	private int idCliente;
	private String Placa;
	
	
	public Carro(int idCarro, int idModelo, int idCliente, String placa) {
		this.idCarro = idCarro;
		this.idModelo = idModelo;
		this.idCliente = idCliente;
		Placa = placa;
	}


	public int getIdCarro() {
		return idCarro;
	}


	public int getIdModelo() {
		return idModelo;
	}


	public int getIdCliente() {
		return idCliente;
	}


	public String getPlaca() {
		return Placa;
	}

	
}


