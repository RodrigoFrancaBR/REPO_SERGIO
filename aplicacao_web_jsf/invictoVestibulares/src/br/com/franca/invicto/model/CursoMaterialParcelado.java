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

		BigDecimal desconto = contrato.getValorCurso().multiply(BigDecimal.valueOf(contrato.getDescontoCurso()));

		BigDecimal cursoComDesconto = contrato.getValorCurso().subtract(desconto);

		BigDecimal cursoComDescontoParcelado = cursoComDesconto
				.divide(BigDecimal.valueOf(contrato.getQtdParcelasCurso()), 2, BigDecimal.ROUND_DOWN);

		BigDecimal taxaMatricula = contrato.getTaxaMatricula();
		
		BigDecimal residualDaParcelaCurso = cursoComDesconto
				.subtract(cursoComDescontoParcelado.multiply(BigDecimal.valueOf(contrato.getQtdParcelasCurso())));

		BigDecimal parcelaDoMaterial = contrato.getValorMaterial()
				.divide(BigDecimal.valueOf(contrato.getQtdParcelasMaterial()), 2, BigDecimal.ROUND_DOWN);

		BigDecimal residualDaParcelaMaterial = contrato.getValorMaterial().subtract(
				parcelaDoMaterial.multiply(BigDecimal.valueOf(contrato.getQtdParcelasMaterial())));
		
		contrato.setResidualDaParcelaDoCurso(residualDaParcelaCurso);
		contrato.setResidualDaParcelaDoMaterial(residualDaParcelaMaterial);
		

		Calendar proximoVencimento = Calendar.getInstance();

		parcela.setNumeroDaParcela(1);

		// primeira cobrança do curso
		parcela.setNumeroDaParcelaCurso(1);

		// sem cobrança de material na primeira parcela
		parcela.setNumeroDaParcelaMaterial(0);

		// sem residuo na primeira parcela
		parcela.setValorResidualDaParcelaCurso(BigDecimal.valueOf(0));

		// sem cobran�a de material na primeira parcela
		parcela.setValorDaParcelaDoMaterial(BigDecimal.valueOf(0));

		// sem residuo na primeira parcela
		parcela.setValorResidualDaParcelaMaterial(BigDecimal.valueOf(0));

		parcela.setValorTotalDaParcela(cursoComDescontoParcelado.add(taxaMatricula));

		parcela.setValorDaParcelaDoCurso(cursoComDescontoParcelado.add(taxaMatricula));

		parcela.setValorPago(parcela.getValorTotalDaParcela());

		parcela.setDataPagamento(Calendar.getInstance());

		parcela.setSituacao(Situacao.PAGO);

		// primeira parcela paga no ato
		parcela.setDataVencimento(Calendar.getInstance());

		parcelas.add(parcela);

		// proximoVencimento.set(Calendar.DAY_OF_MONTH, contrato.getDiaVencimento());

		int diferenca = Math.abs(contrato.getDiaVencimento() - Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

		if (diferenca >= 20)
			proximoVencimento.add(Calendar.MONTH, 1);

		for (int i = 2; (i <= contrato.getQtdParcelasCurso() || i <= contrato.getQtdParcelasMaterial() + 1); i++) {

			parcela = new Parcela();
			// segunda parcela

			parcela.setNumeroDaParcela(i);

			proximoVencimento = Calendar.getInstance();

			proximoVencimento.set(Calendar.DAY_OF_MONTH, contrato.getDiaVencimento());

			proximoVencimento.add(Calendar.MONTH, i - 1);

			parcela.setDataVencimento(proximoVencimento);

			// garante que inicie com zero
			parcela.setValorDaParcelaDoCurso(BigDecimal.valueOf(0));
			parcela.setValorResidualDaParcelaCurso(BigDecimal.valueOf(0));
			parcela.setValorDaParcelaDoMaterial(BigDecimal.valueOf(0));
			parcela.setValorResidualDaParcelaMaterial((BigDecimal.valueOf(0d)));
			// sem taxa de matricula
			parcela.setTaxaMatricula(BigDecimal.valueOf(0));

			// verifico se tem parcelas de curso
			if (i <= contrato.getQtdParcelasCurso()) {

				// segunda parcela do curso
				parcela.setNumeroDaParcelaCurso(i);
				parcela.setValorDaParcelaDoCurso(cursoComDescontoParcelado);

				/*
				 * parcela.setValorDaParcelaDoCurso(cursoComDesconto
				 * .divide(BigDecimal.valueOf(contrato.getQtdParcelasCurso()), 2,
				 * BigDecimal.ROUND_DOWN));
				 */

				// verificar se eh a ultima parcela do curso
				if (i == contrato.getQtdParcelasCurso()) {
					// valorResidualDaParcelaCurso =
					// cursoComDesconto.subtract(cursoComDescontoParcelado.multiply(BigDecimal.valueOf(contrato.getQtdParcelasCurso())));
					// parcela.setValorResidualDaParcelaCurso(cursoComDesconto.subtract(cursoComDescontoParcelado.multiply(BigDecimal.valueOf(contrato.getQtdParcelasCurso()))));

					// valor da parcela com o residual na ultima parcela
					parcela.setValorDaParcelaDoCurso(cursoComDescontoParcelado.add(residualDaParcelaCurso));

					/*
					 * parcela.setValorDaParcelaDoCurso(parcela.getValorDaParcelaDoCurso() .add
					 * (parcela.getValorResidualDaParcelaCurso()));
					 */
				}

			} else {
				parcela.setNumeroDaParcelaCurso(0);
			}

			// verifico se tem parcelas de Material
			if (i <= contrato.getQtdParcelasMaterial() + 1) {

				// pois a primeira parcela nunca entra a parcela do material apenas a do curso
				parcela.setNumeroDaParcelaMaterial(i - 1);

				parcela.setValorDaParcelaDoMaterial(parcelaDoMaterial);

				/*
				 * parcela.setValorDaParcelaDoMaterial(contrato.getValorMaterial()
				 * .divide(BigDecimal.valueOf(contrato.getQtdParcelasMaterial()), 2,
				 * BigDecimal.ROUND_DOWN));
				 */

				// verifico se eh a ultima do material
				if (i == contrato.getQtdParcelasMaterial() + 1) {

					parcela.setValorResidualDaParcelaMaterial(residualDaParcelaMaterial);

					// valor da parcela com o residual na ultima parcela
					parcela.setValorDaParcelaDoMaterial(parcelaDoMaterial.add(residualDaParcelaMaterial));

					/*
					 * parcela.setValorDaParcelaDoMaterial(
					 * parcela.getValorDaParcelaDoMaterial().add(parcela.
					 * getValorResidualDaParcelaMaterial()));
					 */
				}
			} else {
				parcela.setNumeroDaParcelaMaterial(0);
			}

			parcela.setValorTotalDaParcela(
					parcela.getValorDaParcelaDoCurso().add(parcela.getValorDaParcelaDoMaterial()));

			/*
			 * parcela.setValorTotalDaParcela( parcela.getValorDaParcelaDoCurso()
			 * .add(parcela.getValorResidualDaParcelaCurso()
			 * .add(parcela.getValorDaParcelaDoMaterial()
			 * .add(parcela.getValorResidualDaParcelaMaterial()))));
			 */

			parcela.setValorPago(BigDecimal.valueOf(0));

			parcela.setDataPagamento(null);

			parcela.setSituacao(Situacao.A_VENCER);

			parcelas.add(parcela);
		}

		return parcelas;
	}

}
