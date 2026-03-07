package models;

import java.util.Date;

public class OrdemDeServico {
	private int idOrdemServico;
	private int idCarro;
	private int statusOS;
	private Date dataCadastro;
	
	public OrdemDeServico(int idOrdemServico, int idCarro, int statusOS, Date dataCadastro) {
		this.idOrdemServico = idOrdemServico;
		this.idCarro = idCarro;
		this.statusOS = statusOS;
		this.dataCadastro = dataCadastro;
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
	
	
	
	
}


