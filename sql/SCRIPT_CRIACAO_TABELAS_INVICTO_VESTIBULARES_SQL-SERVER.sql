-- -----------------------------------------------------
-- Table TB_UNIDADE
-- -----------------------------------------------------

CREATE TABLE TB_UNIDADE (
  id_unidade BIGINT IDENTITY(1,1) PRIMARY KEY NOT NULL,
  nome VARCHAR(100) NOT NULL,
  endereco VARCHAR(100) NOT NULL UNIQUE,
  ativo VARCHAR (7)NOT NULL -- USADO PARA EXCLUSÃO LÓGICA Ativo/Inativo
  );
-- -----------------------------------------------------
-- Table TB_TURMA
-- -----------------------------------------------------

  CREATE TABLE TB_TURMA (
  id_turma BIGINT IDENTITY(1,1) PRIMARY KEY NOT NULL,
  nome VARCHAR(100)  NOT NULL UNIQUE,  
  unidade_id BIGINT NOT NULL,
  ativo VARCHAR (7)NOT NULL -- USADO PARA EXCLUSÃO LÓGICA Ativo/Inativo

  INDEX FK_TB_TURMA_TB_UNIDADE_idx (unidade_id ASC),  
  
  CONSTRAINT FK_TB_TURMA_TB_UNIDADE
    FOREIGN KEY (unidade_id)
    REFERENCES TB_UNIDADE (id_unidade)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

-- -----------------------------------------------------
-- Table TB_ALUNO
-- -----------------------------------------------------

CREATE TABLE TB_ALUNO (
  id_aluno BIGINT IDENTITY(1,1) PRIMARY KEY NOT NULL,
  turma_id BIGINT NOT NULL, --'TURMA QUE O ALUNO ESCOLHEU PARA ESTUDAR'
  nome VARCHAR(45) NOT NULL,
  cpf VARCHAR(45) NOT NULL UNIQUE,
  rg VARCHAR(45) NOT NULL,
  orgao_exp VARCHAR(45) NOT NULL,
  uf_rg VARCHAR(45) NOT NULL,
  data_nascimento VARCHAR(45) NOT NULL,
  sexo VARCHAR(45) NOT NULL,
  celular VARCHAR(45) NOT NULL,
  residencial VARCHAR(45) NOT NULL,
  email VARCHAR(45) NOT NULL,
  cep VARCHAR(45) NOT NULL,
  endereco VARCHAR(45) NOT NULL,
  bairro VARCHAR(45) NOT NULL,
  cidade VARCHAR(45) NOT NULL,
  estado VARCHAR(45) NOT NULL,  
  pai VARCHAR(45) NOT NULL,
  mae VARCHAR(45) NOT NULL,
  ativo VARCHAR (7)NOT NULL, -- USADO PARA EXCLUSÃO LÓGICA Ativo/Inativo  

  INDEX FK_TB_PESSOA_has_TB_TURMA_TB_TURMA_idx (turma_id ASC),  

  CONSTRAINT FK_TB_PESSOA_has_TB_TURMA_TB_TURMA
    FOREIGN KEY (turma_id)
    REFERENCES TB_TURMA (id_turma)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

-- -----------------------------------------------------
-- Table TB_CONTRATO
-- -----------------------------------------------------

  CREATE TABLE TB_CONTRATO (
  id_contrato BIGINT IDENTITY(1,1) PRIMARY KEY NOT NULL,
  taxa_matricula DECIMAL (7,2) NOT NULL,
  valor_curso DECIMAL (7,2) NOT NULL,
  desconto_curso DECIMAL (7,2) NOT NULL, --'DE O ATÉ 100 %'
  qtd_parcelas_curso TINYINT NOT NULL, --'1 =  AVISTA, x = NUMERO DE PARCELAS \nobs ACIMA DE 2 JÁ É PARCELADO',
  valor_material DECIMAL (7,2) NOT NULL,
  qtd_parcelas_material TINYINT NOT NULL, --'1 =  AVISTA, x = NUMERO DE PARCELAS \nobs ACIMA DE 2 JÁ É PARCELADO',
  dia_vencimento TINYINT NOT NULL, --'APENAS O DIA\n1,5,10 etc.',
  forma_pagamento VARCHAR(45) NOT NULL, -- '1= DINHEIRO, 2= CARTAO-CREDITO, 3= CARTAO-DEBITO, 4= CHEQUE',
  data_matricula DATE NOT NULL,
  situacao_matricula VARCHAR(45) NOT NULL, -- 0 = PROCESSO DE MATRÍCULA, 1= ATIVO/MATRICULADO, 2 = CANCELADO, 3 =  ACORDO , 4 = ENCERRADO',
  matricula VARCHAR(45) NOT NULL,
  aluno_id BIGINT NOT NULL,
  condicao_contrato VARCHAR(45) NOT NULL, -- 'CURSO E MATERIAL ÁVISTA',  

  INDEX FK_TB_CONTRATO_TB_ALUNO1_idx (aluno_id ASC),
 
  CONSTRAINT FK_TB_CONTRATO_TB_ALUNO
    FOREIGN KEY (aluno_id)
    REFERENCES TB_ALUNO (id_aluno)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- -----------------------------------------------------
-- Table INVICTO_DB.TB_PARCELA
-- -----------------------------------------------------
CREATE TABLE TB_PARCELA (
  id_parcela BIGINT IDENTITY(1,1) PRIMARY KEY NOT NULL,
  contrato_id BIGINT NOT NULL,
  numero_parcela_curso TINYINT NOT NULL, --'PARCELA DE NUMERO 1 PARCELA DE NUMERO 2 E ETC..',
  numero_parcela_material TINYINT NOT NULL, --PARCELA DE NUMERO 1 PARCELA DE NUMERO 2 E ETC..\'',
  data_vencimento DATE NOT NULL,
  valor_pago DECIMAL (7,2) NOT NULL, -- VALOR PAGO NESTA DATA\'',
  data_pagamento DATE NULL,
  valor_parcela_curso DECIMAL (7,2) NOT NULL, -- 'VALOR COM DESCONTO E PARCELADO',
  valor_parcela_material DECIMAL (7,2) NOT NULL, -- 'VALOR PARCELADO',
  valor_total_parcela DECIMAL (7,2) NOT NULL, -- 'PARCELA_CURSO+PARCELA_MATERIAL+TAXA_MATRICULA',
  situacao_parcela VARCHAR(45) NOT NULL, -- '1= PAGO, 2 = A VENCER, 3 = ATRASADO 4 = CANCELADO POR ACORDO',
  numero_parcela TINYINT NOT NULL, -- 'a primeira parcela contem a 1ª parcela de curso + 0ª parcela de Material. Na segunda parcela contem a 2ª parcela de curso + 1ª parcela de Material. Na terceira parcela contem a 3ª parcela de curso + 2ª parcela de Material e etc..',
  taxa_matricula DECIMAL (7,2) NOT NULL,
  valor_residual_curso DECIMAL (7,2) NOT NULL,
  valor_residual_material DECIMAL (7,2) NOT NULL,
  
  INDEX FK_PARCELAS_MATRICULA_idx (contrato_id ASC),
  
  CONSTRAINT FK_PARCELAS_MATRICULA
    FOREIGN KEY (contrato_id)
    REFERENCES TB_CONTRATO (id_contrato)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- -----------------------------------------------------
-- Table TB_CATEGORIA
-- -----------------------------------------------------

CREATE TABLE TB_CATEGORIA (
  id_categoria BIGINT IDENTITY(1,1) PRIMARY KEY NOT NULL,
  nome VARCHAR(45) NOT NULL,
  tipo_categoria VARCHAR(45) NOT NULL, --0 = indefinida, 1 = FUNCIONARIO, 2 = ESSENCIAL, 3 = ESCRITORIO, 4 = TERCEIROS  
  ativo VARCHAR (7)NOT NULL -- USADO PARA EXCLUSÃO LÓGICA Ativo/Inativo
  );

  -- -----------------------------------------------------
-- Table TB_FUNCIONARIO
-- -----------------------------------------------------

  CREATE TABLE TB_FUNCIONARIO (
  id_funcionario BIGINT IDENTITY(1,1) PRIMARY KEY NOT NULL,
  matricula VARCHAR(45) NOT NULL,
  situacao VARCHAR(45) NOT NULL, -- '1 = CONTRATADO, 2 = DEMITIDO, 3 = APOSENTADO, 4= NAO TRABALHA MAIS/PEDIU DEMISSÃO',
  cargo VARCHAR(45) NOT NULL, --'0= não é professor, 1= professor',
  nome VARCHAR(45) NOT NULL,  
  cpf VARCHAR(45) NOT NULL,  
  rg VARCHAR(45),
  orgao_exp VARCHAR(45),
  uf_rg VARCHAR(45),
  data_nascimento VARCHAR(45),
  sexo VARCHAR(45),
  celular VARCHAR(45) NOT NULL,
  residencial VARCHAR(45),
  email VARCHAR(45) NOT NULL,
  cep VARCHAR(45),
  endereco VARCHAR(45) NOT NULL,
  bairro VARCHAR(45) NOT NULL,
  cidade VARCHAR(45),
  estado VARCHAR(45),
  ativo VARCHAR (7)NOT NULL -- USADO PARA EXCLUSÃO LÓGICA Ativo/Inativo
  );

 ---- -----------------------------------------------------
-- Table TB_DESPESA
-- -----------------------------------------------------

  CREATE TABLE TB_DESPESA
(
    id_despesa BIGINT IDENTITY(1,1) PRIMARY KEY NOT NULL,
  funcionario_id BIGINT NULL,
  categoria_id BIGINT NOT NULL,
    valor_despesa DECIMAL (7,2) NOT NULL,
  data_vencimento DATE NOT NULL,
  via_recebido VARCHAR(45) NOT NULL,
  ativo VARCHAR (7)NOT NULL -- USADO PARA EXCLUSÃO LÓGICA Ativo/Inativo

  INDEX FK_DESPESA_FUNCIONARIO_idx (funcionario_id ASC),
  INDEX FK_TB_DESPESA_TB_CATEGORIA_idx (categoria_id ASC),
  
  CONSTRAINT FK_DESPESA_FUNCIONARIO
    FOREIGN KEY (funcionario_id)
    REFERENCES TB_FUNCIONARIO (id_funcionario)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,

  CONSTRAINT FK_TB_DESPESA_TB_CATEGORIA
    FOREIGN KEY (categoria_id)
    REFERENCES TB_CATEGORIA (id_categoria)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


 ---- -----------------------------------------------------
-- Table TB_LANCAMENTO_DESPESA
-- -----------------------------------------------------

CREATE TABLE TB_LANCAMENTO_DESPESA
(
  id_lancamento_despesa BIGINT IDENTITY(1,1) PRIMARY KEY NOT NULL,
  despesa_id BIGINT NOT NULL,
  data_vencimento DATE NOT NULL,
  valor_despesa DECIMAL(7,2) NOT NULL,  
  data_pagamento DATE NULL,
  valor_pago DECIMAL(7,2) NULL,
  situacao_lancamento  VARCHAR(9) NOT NULL, -- Pendente/Pago
  data_emissao DATE NOT NULL -- Data que foi gerado o lancamento
  
INDEX FK_TB_LANCAMENTO_DESPESA_DESPESA_idx (despesa_id ASC),

CONSTRAINT FK_TB_LANCAMENTO_DESPESA_DESPESA
    FOREIGN KEY (despesa_id)
    REFERENCES TB_DESPESA (id_despesa)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
  );
