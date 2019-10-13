package br.com.franca.invicto.model;

import java.util.List;

public interface CondicaoDoContrato {
	public List<Parcela> calculaParcelas(Contrato contrato);
}
