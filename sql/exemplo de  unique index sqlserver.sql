CREATE UNIQUE INDEX uk_nome_turma
ON TB_TURMA (nome, turno,unidade_id);

CREATE UNIQUE INDEX uk_nome_endereco
ON TB_UNIDADE (nome, endereco);

DROP INDEX uk_nome_turma ON TB_TURMA;

DROP INDEX uk_nome_endereco ON TB_UNIDADE;
