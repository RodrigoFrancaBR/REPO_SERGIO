package br.com.franca.invicto.model;

public enum CondicaoContrato {
	// Turma, Unidade	
		
	CURSO_AVISTA_MATERIAL_PARCELADO(1, "Curso Ávista e Material Parcelado"),
	CURSO_MATERIAL_AVISTA(2, "Curso e Material Ávista"),
	CURSO_MATERIAL_PARCELADO(3, "Curso e Material Parcelado"),
	CURSO_PARCELADO_MATERIAL_AVISTA(4, "Curso Parcelado e Material Ávista");

		private final int codigo;
		private final String descricao;

		private CondicaoContrato(int codigo, String descricao) {
			this.codigo = codigo;
			this.descricao = descricao;
		}

		public static CondicaoContrato valueOf(int codigo) {
			switch (codigo) {			
			case 1:
				return CURSO_AVISTA_MATERIAL_PARCELADO;
			case 2:
				return CURSO_MATERIAL_AVISTA;
			case 3:
				return CURSO_MATERIAL_PARCELADO;
			case 4:
				return CURSO_PARCELADO_MATERIAL_AVISTA;
			default:
				break;
			}
			return null;
		}	

		public int getCodigo() {
			return this.codigo;
		}

		public String getDescricao() {
			return this.descricao;
		}
}
