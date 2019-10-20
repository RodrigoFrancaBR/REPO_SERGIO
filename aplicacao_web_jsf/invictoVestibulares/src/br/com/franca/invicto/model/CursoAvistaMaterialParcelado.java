package br.com.franca.invicto.model;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CursoAvistaMaterialParcelado implements CondicaoDoContrato {

	@Override
	public String toString() {
		return "CursoAvistaMaterialParcelado";
	}

	@Override
	public List<Parcela> calculaParcelas(Contrato contrato) {
		Parcela parcela = new Parcela();
		List<Parcela> parcelas = new ArrayList<Parcela>();
		
		BigDecimal desconto = contrato.getValorCurso().multiply(BigDecimal.valueOf(contrato.getDescontoCurso()));
		
		BigDecimal cursoComDescontoAvista = contrato.getValorCurso().subtract(desconto);
		
		BigDecimal taxaMatricula = contrato.getTaxaMatricula();
		
		Calendar proximoVencimento = Calendar.getInstance();
		
		BigDecimal parcelaDoMaterial = contrato.getValorMaterial()
				.divide(BigDecimal.valueOf(contrato.getQtdParcelasMaterial()), 2, BigDecimal.ROUND_DOWN);

		BigDecimal residualDaParcelaMaterial = contrato.getValorMaterial().subtract(
				parcelaDoMaterial.multiply(BigDecimal.valueOf(contrato.getQtdParcelasMaterial())));
		
		contrato.setResidualDaParcelaDoCurso(BigDecimal.valueOf(0));
		
		contrato.setResidualDaParcelaDoMaterial(residualDaParcelaMaterial);


		parcela.setNumeroDaParcela(1);

		parcela.setNumeroDaParcelaCurso(0);

		parcela.setNumeroDaParcelaMaterial(0);

		parcela.setDataVencimento(Calendar.getInstance());
				
		parcela.setValorResidualDaParcelaCurso(BigDecimal.valueOf(0));

		parcela.setValorDaParcelaDoMaterial(BigDecimal.valueOf(0));

		parcela.setValorResidualDaParcelaMaterial(BigDecimal.valueOf(0));

		// parcela.setTaxaMatricula(contrato.getTaxaMatricula());
		
		parcela.setValorDaParcelaDoCurso(cursoComDescontoAvista.add(taxaMatricula));
		
		parcela.setValorTotalDaParcela(cursoComDescontoAvista.add(taxaMatricula));

		/*
		 * parcela.setValorTotalDaParcela(parcela.getValorDaParcelaDoCurso().add(parcela
		 * .getValorDaParcelaDoMaterial()) .add(parcela.getTaxaMatricula()));
		 */

		parcela.setValorPago(parcela.getValorTotalDaParcela());

		parcela.setDataPagamento(Calendar.getInstance());

		parcela.setSituacao(Situacao.PAGO);

		parcelas.add(parcela);		

		// proximoVencimento.set(Calendar.DAY_OF_MONTH, contrato.getDiaVencimento());

		int diferenca = Math.abs(contrato.getDiaVencimento() - Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

		if (diferenca >= 20)
			proximoVencimento.add(Calendar.MONTH, 1);

		for (int i = 1; i <= contrato.getQtdParcelasMaterial(); i++) {

			parcela = new Parcela();

			parcela.setNumeroDaParcela(i + 1);

			parcela.setNumeroDaParcelaCurso(0);

			parcela.setNumeroDaParcelaMaterial(i);

			proximoVencimento = Calendar.getInstance();

			proximoVencimento.set(Calendar.DAY_OF_MONTH, contrato.getDiaVencimento());

			proximoVencimento.add(Calendar.MONTH, i);

			parcela.setDataVencimento(proximoVencimento);

			parcela.setValorDaParcelaDoCurso((BigDecimal.valueOf(0)));

			parcela.setValorResidualDaParcelaCurso((BigDecimal.valueOf(0)));
			
			parcela.setTaxaMatricula(BigDecimal.valueOf(0));

			parcela.setValorDaParcelaDoMaterial(parcelaDoMaterial);			
			
			/*
			 * parcela.setValorDaParcelaDoMaterial(contrato.getValorMaterial()
			 * .divide(BigDecimal.valueOf(contrato.getQtdParcelasMaterial()), 2,
			 * BigDecimal.ROUND_DOWN));
			 */

			parcela.setValorResidualDaParcelaMaterial(BigDecimal.valueOf(0));

			if (i == contrato.getQtdParcelasMaterial()) {
				
				parcela.setValorDaParcelaDoMaterial(parcelaDoMaterial.add(residualDaParcelaMaterial));

				/*
				 * parcela.setValorResidualDaParcelaMaterial(contrato.getValorMaterial()
				 * .subtract(parcela.getValorDaParcelaDoMaterial()
				 * .multiply(BigDecimal.valueOf(contrato.getQtdParcelasMaterial()))));
				 */				
				
				
				/*
				 * parcela.setValorDaParcelaDoMaterial( parcela.getValorDaParcelaDoMaterial()
				 * .add(parcela.getValorResidualDaParcelaMaterial()));
				 */
			}
			
			/*
			 * parcela.setValorTotalDaParcela(
			 * parcela.getValorResidualDaParcelaMaterial().add(parcela.
			 * getValorDaParcelaDoMaterial()));
			 */

			
			parcela.setValorTotalDaParcela(
					parcela.getValorDaParcelaDoMaterial());
			
			parcela.setValorPago(BigDecimal.valueOf(0));

			parcela.setDataPagamento(null);

			parcela.setSituacao(Situacao.A_VENCER);

			parcelas.add(parcela);
		}
		return parcelas;
		// contrato.setParcelas(parcelas);
	}
}
