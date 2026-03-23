package models;

import java.math.BigDecimal;

public class ItemOS {
	private int idItemOS;
	private int idOrdemDeServico;
	private int idServico;
	private BigDecimal valorCobrado;
	
	public ItemOS() {
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

	public void setIdItemOS(int idItemOS) {
		this.idItemOS = idItemOS;
	}

	public void setIdOrdemDeServico(int idOrdemDeServico) {
		this.idOrdemDeServico = idOrdemDeServico;
	}

	public void setIdServico(int idServico) {
		this.idServico = idServico;
	}

	public BigDecimal getValorCobrado() {
		return valorCobrado;
	}

	public void setValorCobrado(BigDecimal valorCobrado) {
		this.valorCobrado = valorCobrado;
	}

	
	
	
	
}
