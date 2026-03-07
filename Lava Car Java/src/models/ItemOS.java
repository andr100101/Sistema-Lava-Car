package models;

public class ItemOS {
	private int idItemOS;
	private int idOrdemDeServico;
	private int idServico;
	
	public ItemOS(int idItemOS, int idOrdemDeServico, int idServico) {
		this.idItemOS = idItemOS;
		this.idOrdemDeServico = idOrdemDeServico;
		this.idServico = idServico;
	}

	public int getIdItemOS() {
		return idItemOS;
	}

	public int getIdOrdemDeServico() {
		return idOrdemDeServico;
	}

	public int getIdServico() {
		return idServico;
	}

	
}
