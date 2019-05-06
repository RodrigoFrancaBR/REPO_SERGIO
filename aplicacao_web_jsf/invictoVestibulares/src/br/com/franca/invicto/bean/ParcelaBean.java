package br.com.franca.invicto.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.management.RuntimeErrorException;

import br.com.franca.invicto.dao.ContratoDAO;
import br.com.franca.invicto.dao.ParcelaDAO;
import br.com.franca.invicto.model.CondicaoDoContrato;
import br.com.franca.invicto.model.Contrato;
import br.com.franca.invicto.model.Parcela;

@ManagedBean
@SessionScoped
public class ParcelaBean extends CrudBean<Parcela, ParcelaDAO> {
	private ParcelaDAO parcelaDao;	
	private Contrato contrato = new Contrato();

	@Override
	public ParcelaDAO getDao() {
		if (parcelaDao == null) {
			parcelaDao = new ParcelaDAO();
		}
		return parcelaDao;
	}

	@Override
	public Parcela criarNovaEntidade() {
		return new Parcela();
	}

	public void consultar() {
		Contrato contrato = new ContratoDAO().buscarPorMatricula(entidade
				.getContrato().getMatricula());
		
		if (null == contrato){
			throw new RuntimeException("Contrato não Encontrato");
		}else {
			CondicaoDoContrato cc = contrato.getCondicaoDoContrato();
			List<Parcela> parcelas = cc.calculaParcelas(contrato);
			
			/*List<Parcela> parcelas = contrato.getCondicaoDoContrato()
					.calculaParcelas(contrato);*/
			
			contrato.setParcelas(parcelas);
			
			entidade.setContrato(contrato);
		}		
	}
	
	public void receberPagamento(Parcela parcela){				
		getDao().receberPagamento(parcela);
		buscar();
	}	

	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

}
