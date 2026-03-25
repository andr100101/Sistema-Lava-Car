package models;

public class Carro {
	private int idCarro;
	private int idModelo;
	private int idCliente;
	private String Placa;

	public Carro() {

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

	public void setIdCarro(int idCarro) {
		this.idCarro = idCarro;
	}

	public void setIdModelo(int idModelo) {
		this.idModelo = idModelo;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public void setPlaca(String placa) {
		Placa = placa;
	}

}
