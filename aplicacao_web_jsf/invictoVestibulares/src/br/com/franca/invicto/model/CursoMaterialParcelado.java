package br.com.franca.invicto.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CursoMaterialParcelado implements CondicaoDoContrato {

	@Override
	public String toString() {
		return "CursoMaterialParcelado";
	}

	@Override
	public List<Parcela> calculaParcelas(Contrato contrato) {
		Parcela parcela = new Parcela();
		List<Parcela> parcelas = new ArrayList<Parcela>();

		parcela.setNumeroDaParcela(1);

		// primeira cobrança do curso
		parcela.setNumeroDaParcelaCurso(1);

		// sem cobrança de material na primeira parcela
		parcela.setNumeroDaParcelaMaterial(0);

		// configura a data da matricula para o dia de vencimento 
		parcela.setDataVencimento(contrato.getDataMatricula());

		// configura o dia atual para data de vencimento
		//parcela.setDataVencimento(Calendar.getInstance());

		BigDecimal desconto = contrato.getValorCurso().multiply(
				BigDecimal.valueOf(contrato.getDescontoCurso()));

		BigDecimal cursoComDesconto = contrato.getValorCurso().subtract(
				desconto);

		parcela.setValorDaParcelaDoCurso(cursoComDesconto.divide(
				BigDecimal.valueOf(contrato.getQtdParcelasCurso()), 2,
				BigDecimal.ROUND_DOWN));

		parcela.setValorResidualDaParcelaCurso((BigDecimal.valueOf(0)));

		// sem cobrança de material na primeira parcela
		parcela.setValorDaParcelaDoMaterial(BigDecimal.valueOf(0));

		// sem residuo na primeira parcela
		parcela.setValorResidualDaParcelaMaterial((BigDecimal.valueOf(0)));

		parcela.setTaxaMatricula(contrato.getTaxaMatricula());

		parcela.setValorTotalDaParcela(parcela.getValorDaParcelaDoCurso().add(
				parcela.getTaxaMatricula()));

		parcela.setValorPago(parcela.getValorTotalDaParcela());

		//parcela.setDataPagamento(Calendar.getInstance());
		
		parcela.setDataPagamento(parcela.getDataVencimento());

		parcela.setSituacaoDaParcela("Pago");

		parcelas.add(parcela);

		Calendar proximoVencimento = Calendar.getInstance();		

		proximoVencimento.set(Calendar.DAY_OF_MONTH,
				contrato.getDiaVencimento());
		
		int diferenca = Math.abs(contrato.getDiaVencimento()
				- contrato.getDataMatricula().get(Calendar.DAY_OF_MONTH));

//		int diferenca = Math.abs(contrato.getDiaVencimento()
//				- Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

		if (diferenca >= 20)
			proximoVencimento.add(Calendar.MONTH, 1);

		for (int i = 2; (i <= contrato.getQtdParcelasCurso() || i <= contrato
				.getQtdParcelasMaterial() + 1); i++) {			

			parcela = new Parcela();

			// segunda parcela

			parcela.setNumeroDaParcela(i);

			proximoVencimento = Calendar.getInstance();

			proximoVencimento.set(Calendar.DAY_OF_MONTH,
					contrato.getDiaVencimento());

			proximoVencimento.add(Calendar.MONTH, i - 1);

			parcela.setDataVencimento(proximoVencimento);

			parcela.setValorDaParcelaDoCurso(BigDecimal.valueOf(0));

			parcela.setValorResidualDaParcelaCurso(BigDecimal.valueOf(0));

			parcela.setValorDaParcelaDoMaterial(BigDecimal.valueOf(0));

			parcela.setValorResidualDaParcelaMaterial((BigDecimal.valueOf(0d)));

			parcela.setTaxaMatricula(BigDecimal.valueOf(0));

			// verifico se tem parcelas de curso
			if (i <= contrato.getQtdParcelasCurso()) {
				// segunda parcela do curso
				parcela.setNumeroDaParcelaCurso(i);

				parcela.setValorDaParcelaDoCurso(cursoComDesconto.divide(
						BigDecimal.valueOf(contrato.getQtdParcelasCurso()), 2,
						BigDecimal.ROUND_DOWN));

				// verificar se eh a ultima parcela do curso
				if (i == contrato.getQtdParcelasCurso()) {

					parcela.setValorResidualDaParcelaCurso(cursoComDesconto
							.subtract(parcela.getValorDaParcelaDoCurso()
									.multiply(
											BigDecimal.valueOf(contrato
													.getQtdParcelasCurso()))));
				}

			}else {
				parcela.setNumeroDaParcelaCurso(0);
			}

			// verifico se tem parcelas de Material
			if (i <= contrato.getQtdParcelasMaterial() + 1) {

				parcela.setNumeroDaParcelaMaterial(i - 1);

				parcela.setValorDaParcelaDoMaterial(contrato.getValorMaterial()
						.divide(BigDecimal.valueOf(contrato
								.getQtdParcelasMaterial()), 2,
								BigDecimal.ROUND_DOWN));

				// verifico se eh a ultima do material

				if (i == contrato.getQtdParcelasMaterial() + 1) {

					parcela.setValorResidualDaParcelaMaterial(contrato
							.getValorMaterial()
							.subtract(
									parcela.getValorDaParcelaDoMaterial()
											.multiply(
													BigDecimal.valueOf(contrato
															.getQtdParcelasMaterial()))));
				}
			}else{
				parcela.setNumeroDaParcelaMaterial(0);
			}

			parcela.setValorTotalDaParcela(parcela
					.getValorDaParcelaDoCurso()
					.add(parcela
							.getValorResidualDaParcelaCurso()
							.add(parcela
									.getValorDaParcelaDoMaterial()
									.add(parcela
											.getValorResidualDaParcelaMaterial()))));

			parcela.setValorPago(BigDecimal.valueOf(0));

			parcela.setDataPagamento(null);

			parcela.setSituacaoDaParcela("A Vencer");

			parcelas.add(parcela);
		}

		return parcelas;
	}

}
