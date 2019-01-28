package br.com.franca.invicto.model;

import java.util.Calendar;

public class Lancamento {

	private Calendar dataInicio = Calendar.getInstance();
	private Calendar dataFim = Calendar.getInstance();

	public Calendar getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Calendar dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Calendar getDataFim() {
		return dataFim;
	}

	public void setDataFim(Calendar dataFim) {
		this.dataFim = dataFim;
	}

}
