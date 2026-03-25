package models;

import java.util.Date;

public class OrdemDeServico {
	private int idOrdemServico;
	private int idCarro;
	private int statusOS;
	private Date dataCadastro;

	public OrdemDeServico() {
	}

	public int getIdOrdemServico() {
		return idOrdemServico;
	}

	public int getIdCarro() {
		return idCarro;
	}

	public int getStatusOS() {
		return statusOS;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setIdOrdemServico(int idOrdemServico) {
		this.idOrdemServico = idOrdemServico;
	}

	public void setIdCarro(int idCarro) {
		this.idCarro = idCarro;
	}

	public void setStatusOS(int statusOS) {
		this.statusOS = statusOS;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

}
