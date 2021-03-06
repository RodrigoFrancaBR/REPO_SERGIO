package br.com.franca.invicto.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Aluno implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String nome;
	private String cpf;
	private String rg;
	private String orgaoExp;
	private String ufRg;
	private Sexo sexo;	
	private Calendar dataNascimento = Calendar.getInstance();
	private String email;
	private String celular;
	private String residencial;
	private String pai;
	private String mae;
	private String cep;
	private String endereco;
	private String bairro;
	private String cidade;
	private String estado;
	private Situacao situacao;
	private List <Sexo> sexos;

	public enum Sexo {

		FEMININO(0, "Feminino"),

		MASCULINO(1, "Masculino");

		private final int codigo;
		private final String descricao;

		private Sexo(int codigo, String descricao) {
			this.codigo = codigo;
			this.descricao = descricao;
		}

		public int getCodigo() {
			return this.codigo;
		}

		public String getDescricao() {
			return this.descricao;
		}

		public static Sexo getDescricao(int codigo) {
			switch (codigo) {
			case 0:
				return FEMININO;
			case 1:
				return MASCULINO;

			default:
				break;
			}
			return null;
		}

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getOrgaoExp() {
		return orgaoExp;
	}

	public void setOrgaoExp(String orgaoExp) {
		this.orgaoExp = orgaoExp;
	}

	public String getUfRg() {
		return ufRg;
	}

	public void setUfRg(String ufRg) {
		this.ufRg = ufRg;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public Calendar getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getResidencial() {
		return residencial;
	}

	public void setResidencial(String residencial) {
		this.residencial = residencial;
	}

	public String getPai() {
		return pai;
	}

	public void setPai(String pai) {
		this.pai = pai;
	}

	public String getMae() {
		return mae;
	}

	public void setMae(String mae) {
		this.mae = mae;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}
	
	public List<Sexo> getSexos() {
		if (this.sexos == null) {
			this.sexos = new ArrayList<>();
			sexos.add(Sexo.FEMININO);
			sexos.add(Sexo.MASCULINO);
		}
		return sexos;
	}
		

	@Override
	public String toString() {
		return "Aluno [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", rg=" + rg + ", orgaoExp=" + orgaoExp
				+ ", ufRg=" + ufRg + ", sexo=" + sexo + ", dataNascimento=" + dataNascimento + ", email=" + email
				+ ", celular=" + celular + ", residencial=" + residencial + ", pai=" + pai + ", mae=" + mae + ", cep="
				+ cep + ", endereco=" + endereco + ", bairro=" + bairro + ", cidade=" + cidade + ", estado=" + estado
				+ ", situacao=" + situacao + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aluno other = (Aluno) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}