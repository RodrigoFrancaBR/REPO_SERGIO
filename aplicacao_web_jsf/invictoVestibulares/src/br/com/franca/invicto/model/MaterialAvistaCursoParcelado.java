package br.com.franca.invicto.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MaterialAvistaCursoParcelado implements CondicaoDoContrato {

	@Override
	public List<Parcela> calculaParcelas(Contrato contrato) {
		Parcela parcela = new Parcela();
		List<Parcela> parcelas = new ArrayList<Parcela>();

		parcela.setNumeroDaParcela(1);
		
		// primeira cobrança do curso
		parcela.setNumeroDaParcelaCurso(1);
		
		// apenas uma cobrança de material
		parcela.setNumeroDaParcelaMaterial(1);
				
		parcela.setDataVencimento(Calendar.getInstance());
		
		BigDecimal desconto = contrato.getValorCurso().multiply(
				BigDecimal.valueOf(contrato.getDescontoCurso()));
		
		BigDecimal cursoComDesconto = contrato.getValorCurso().subtract(
				desconto);
		
		parcela.setValorDaParcelaDoCurso(cursoComDesconto
				.divide(BigDecimal.valueOf(contrato
						.getQtdParcelasCurso()), 2,
						BigDecimal.ROUND_DOWN));
		
		parcela.setValorResidualDaParcelaCurso((BigDecimal.valueOf(0)));
				
		// unica parcela pois foi avista
		parcela.setValorDaParcelaDoMaterial(contrato.getValorMaterial());		

		// sem residuo pois foi avista 
		parcela.setValorResidualDaParcelaMaterial((BigDecimal.valueOf(0)));
		
		parcela.setTaxaMatricula(contrato.getTaxaMatricula());
				
		parcela.setValorTotalDaParcela(parcela.getValorDaParcelaDoCurso()
				.add(parcela.getValorDaParcelaDoMaterial())
				.add(parcela.getTaxaMatricula()));
		
		parcela.setValorPago(parcela.getValorTotalDaParcela());
		
		parcela.setDataPagamento(Calendar.getInstance());								
				
		parcela.setSituacaoDaParcela("Pago");
						
		parcelas.add(parcela);
		

		Calendar proximoVencimento = Calendar.getInstance();		
		
		proximoVencimento.set(Calendar.DAY_OF_MONTH, contrato
				.getDiaVencimento());			
		
		int diferenca = Math.abs(contrato.getDiaVencimento()
				- Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

		if (diferenca >= 20)
			proximoVencimento.add(Calendar.MONTH, 1);
				

		for (int i = 2; i <= contrato.getQtdParcelasCurso(); i++) {			
			
			parcela = new Parcela();
			// segunda parcela
			parcela.setNumeroDaParcela(i);

			// segunda parcela do curso
			parcela.setNumeroDaParcelaCurso(i);

			// nenhuma cobrança de material pois foi avista
			parcela.setNumeroDaParcelaMaterial(0);
			
			proximoVencimento = Calendar.getInstance();
			
			proximoVencimento.set(Calendar.DAY_OF_MONTH, contrato
					.getDiaVencimento());
			
			proximoVencimento.add(Calendar.MONTH, i-1);
			
			parcela.setDataVencimento(proximoVencimento);
			

			parcela.setValorDaParcelaDoCurso(cursoComDesconto
					.divide(BigDecimal.valueOf(contrato
							.getQtdParcelasCurso()), 2,
							BigDecimal.ROUND_DOWN));
			
			parcela.setValorResidualDaParcelaCurso(BigDecimal.valueOf(0));
			
			parcela.setValorDaParcelaDoMaterial(BigDecimal.valueOf(0));
			
			parcela.setValorResidualDaParcelaMaterial((BigDecimal.valueOf(0d)));
									
			if (i == contrato.getQtdParcelasCurso()) {

				parcela.setValorResidualDaParcelaCurso(cursoComDesconto.subtract(parcela.getValorDaParcelaDoCurso()
						.multiply(BigDecimal.valueOf(contrato.getQtdParcelasCurso()))));
			}
			
			parcela.setTaxaMatricula(BigDecimal.valueOf(0));
			
			parcela.setValorTotalDaParcela(parcela.getValorDaParcelaDoCurso().add(parcela
					.getValorResidualDaParcelaCurso()));
							
			
			parcela.setValorPago(BigDecimal.valueOf(0));
			
			parcela.setDataPagamento(null);
			
			parcela.setSituacaoDaParcela("A Vencer");
			
			parcelas.add(parcela);
		}
		return parcelas;
	}
	

	@Override
	public String toString() {
		return "MaterialAvistaCursoParcelado";
	}

	
}
