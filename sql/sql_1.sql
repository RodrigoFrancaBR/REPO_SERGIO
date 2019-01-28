-- Create Credit table
CREATE TABLE TB_DESPESA
(
    id_despesa BIGINT IDENTITY(1,1) PRIMARY KEY NOT NULL,
	funcionario_id BIGINT NULL,
	categoria_id BIGINT NOT NULL,
    valor_despesa DECIMAL NOT NULL,
	dia_vencimento INT NOT NULL,
	via_recebido VARCHAR(45) NOT NULL,
	ativo TINYINT NOT NULL, -- 'USADO PARA EXCLUSÃO LÓGICA \n0 = desativado, 1 = ativo, ',

	INDEX fk_DESPESA_FUNCIONARIO_idx (funcionario_id ASC),
	INDEX fk_TB_DESPESA_TB_CATEGORIA_idx (categoria_id ASC),
	
	CONSTRAINT fk_DESPESA_FUNCIONARIO
    FOREIGN KEY (funcionario_id)
    REFERENCES TB_FUNCIONARIO (id_funcionario)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,

	CONSTRAINT fk_TB_DESPESA_TB_CATEGORIA
    FOREIGN KEY (categoria_id)
    REFERENCES TB_CATEGORIA (id_categoria)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

    INSERT INTO TB_DESPESA (categoria_id, valor_despesa, dia_vencimento, via_recebido, ativo)
				values (9,1000,15,'correios',1);
				
				SELECT
				C.NOME, C.TIPO_CATEGORIA,
				F.NOME, F.MATRICULA, F.CARGO,
				D.ID_DESPESA, D.FUNCIONARIO_ID, D.CATEGORIA_ID, D.VALOR_DESPESA, D.DIA_VENCIMENTO, D.VIA_RECEBIDO
				 
				FROM
				TB_CATEGORIA AS C,
				TB_FUNCIONARIO AS F,
				TB_DESPESA AS D

				WHERE
				D.CATEGORIA_ID = C.id_categoria
				AND
				D.FUNCIONARIO_ID = F.id_funcionario
				AND 
				D.ATIVO = 1;
				 
				
									
				SELECT * FROM TB_DESPESA;
				SELECT * FROM TB_CATEGORIA;
				SELECT * FROM TB_FUNCIONARIO;
				SELECT * FROM TB_CONTRATO
				SELECT * FROM TB_UNIDADE
				SELECT * FROM TB_ALUNO
				DELETE FROM TB_UNIDADE WHERE id_unidade = 7;
				 