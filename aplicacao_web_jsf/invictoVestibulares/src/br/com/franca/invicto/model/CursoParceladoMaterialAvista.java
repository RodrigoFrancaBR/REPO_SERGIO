package br.com.franca.invicto.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CursoParceladoMaterialAvista implements CondicaoDoContrato {
	
	@Override
	public List<Parcela> calculaParcelas(Contrato contrato) {
		Parcela parcela = new Parcela();

		List<Parcela> parcelas = new ArrayList<Parcela>();

		BigDecimal desconto = contrato.getValorCurso().multiply(BigDecimal.valueOf(contrato.getDescontoCurso()));

		BigDecimal cursoComDesconto = contrato.getValorCurso().subtract(desconto);

		BigDecimal cursoComDescontoParcelado = cursoComDesconto
				.divide(BigDecimal.valueOf(contrato.getQtdParcelasCurso()), 2, BigDecimal.ROUND_DOWN);

		BigDecimal residualDaParcelaCurso = cursoComDesconto
				.subtract(cursoComDescontoParcelado.multiply(BigDecimal.valueOf(contrato.getQtdParcelasCurso())));

		contrato.setResidualDaParcelaDoCurso(residualDaParcelaCurso);

		// material avista
		contrato.setResidualDaParcelaDoMaterial(BigDecimal.valueOf(0));

		Calendar proximoVencimento;

		// primeira parcela
		// parcela.setNumeroDaParcela(1);

		// primeira cobrança do curso
		// parcela.setNumeroDaParcelaCurso(1);

		// apenas uma cobrança de material pois foi avista
		// parcela.setNumeroDaParcelaMaterial(1);

		// parcela.setValorResidualDaParcelaCurso(BigDecimal.valueOf(0));

		// unica parcela pois foi avista
		parcela.setValorDaParcelaDoMaterial(contrato.getValorMaterial());

		parcela.setValorDaParcelaDoCurso(cursoComDescontoParcelado);

		parcela.setValorTotalDaParcela(
				cursoComDescontoParcelado.add(contrato.getValorMaterial()).add(contrato.getTaxaMatricula()));

		parcela.setValorPago(parcela.getValorTotalDaParcela());

		parcela.setDataVencimento(Calendar.getInstance());

		parcela.setDataPagamento(Calendar.getInstance());

		parcela.setSituacao(Situacao.PAGO);

		parcelas.add(parcela);

		for (int i = 2; i <= contrato.getQtdParcelasCurso(); i++) {

			parcela = new Parcela();
			// segunda parcela
			// parcela.setNumeroDaParcela(i);

			// segunda parcela do curso
			// parcela.setNumeroDaParcelaCurso(i);

			// nenhuma cobrança de material pois foi avista
			// parcela.setNumeroDaParcelaMaterial(0);

			proximoVencimento = Calendar.getInstance();

			proximoVencimento.set(Calendar.DAY_OF_MONTH, contrato.getDiaVencimento());

			proximoVencimento.add(Calendar.MONTH, i - 1);

			parcela.setDataVencimento(proximoVencimento);

			parcela.setValorDaParcelaDoCurso(cursoComDescontoParcelado);

			// parcela.setValorResidualDaParcelaCurso(BigDecimal.valueOf(0));

			parcela.setValorDaParcelaDoMaterial(BigDecimal.valueOf(0));

			// parcela.setValorResidualDaParcelaMaterial(BigDecimal.valueOf(0));

			// ultima parcela do curo
			if (i == contrato.getQtdParcelasCurso()) {
				parcela.setValorDaParcelaDoCurso(cursoComDescontoParcelado.add(residualDaParcelaCurso));
			}

			parcela.setValorTotalDaParcela(parcela.getValorDaParcelaDoCurso());

			parcela.setValorPago(BigDecimal.valueOf(0));

			parcela.setDataPagamento(null);

			parcela.setSituacao(Situacao.A_VENCER);

			parcelas.add(parcela);
		}
		return parcelas;
	}

}
