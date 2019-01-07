package br.com.franca.invicto.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CursoMaterialAvista implements CondicaoDoContrato {

	@Override
	public String toString() {
		return "CursoMaterialAvista";
	}

	@Override
	public List <Parcela> calculaParcelas(Contrato contrato) {
		Parcela parcela = new Parcela();
		List<Parcela> parcelas = new ArrayList<Parcela>();

		parcela.setNumeroDaParcela(1);
		// uma cobrança de curso
		parcela.setNumeroDaParcelaCurso(1);
		// uma cobrança de material		
		parcela.setNumeroDaParcelaMaterial(1);
		
		parcela.setDataVencimento(Calendar.getInstance());
		
		BigDecimal desconto = contrato.getValorCurso().multiply(BigDecimal.valueOf(contrato.getDescontoCurso()));
		
		parcela.setValorDaParcelaDoCurso(contrato.getValorCurso().subtract(desconto));
		
		parcela.setValorResidualDaParcelaCurso((BigDecimal.valueOf(0d)));
		
		parcela.setValorDaParcelaDoMaterial(contrato.getValorMaterial());
		
		parcela.setValorResidualDaParcelaMaterial((BigDecimal.valueOf(0d)));
		
		parcela.setTaxaMatricula(contrato.getTaxaMatricula());
		
		parcela.setValorResidualDaParcelaCurso(BigDecimal.valueOf(0));
		
		parcela.setValorResidualDaParcelaMaterial(BigDecimal.valueOf(0));
		
		parcela.setValorTotalDaParcela(parcela.getValorDaParcelaDoCurso()
				.add(parcela.getValorDaParcelaDoMaterial()).add(parcela.getTaxaMatricula()));
		
		parcela.setValorPago(parcela.getValorTotalDaParcela());	
		
		parcela.setDataPagamento(Calendar.getInstance());
		
		parcela.setSituacaoDaParcela("Pago");			
		
		parcelas.add(parcela);

		return parcelas;
	}

}
