-- MySQL Script generated by MySQL Workbench
-- Sun Jan  6 20:20:52 2019
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema DB_INVICTO
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema DB_INVICTO
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `DB_INVICTO` DEFAULT CHARACTER SET utf8 ;
USE `DB_INVICTO` ;

-- -----------------------------------------------------
-- Table `DB_INVICTO`.`TB_UNIDADE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DB_INVICTO`.`TB_UNIDADE` (
  `id_unidade` BIGINT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `endereco` VARCHAR(45) NOT NULL,
  `ativo` TINYINT NOT NULL DEFAULT 1 COMMENT 'USADO PARA EXCLUS�O L�GICA \n0 = desativado, 1 = ativo, ',
  PRIMARY KEY (`id_unidade`),
  UNIQUE INDEX `nome_UNIQUE` (`nome` ASC),
  UNIQUE INDEX `endereco_UNIQUE` (`endereco` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DB_INVICTO`.`TB_TURMA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DB_INVICTO`.`TB_TURMA` (
  `id_turma` BIGINT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `ativo` TINYINT NOT NULL DEFAULT 1 COMMENT 'USADO PARA EXCLUS�O L�GICA \n0 = desativado, 1 = ativo, ',
  `unidade_id` BIGINT NOT NULL,
  `turno` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_turma`),
  UNIQUE INDEX `nome_UNIQUE` (`nome` ASC),
  INDEX `fk_TB_TURMA_TB_UNIDADE1_idx` (`unidade_id` ASC),
  UNIQUE INDEX `turno_UNIQUE` (`turno` ASC),
  CONSTRAINT `fk_TB_TURMA_TB_UNIDADE1`
    FOREIGN KEY (`unidade_id`)
    REFERENCES `DB_INVICTO`.`TB_UNIDADE` (`id_unidade`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DB_INVICTO`.`TB_ALUNO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DB_INVICTO`.`TB_ALUNO` (
  `id_aluno` BIGINT NOT NULL AUTO_INCREMENT,
  `turma_id` BIGINT NOT NULL COMMENT 'TURMA QUE O ALUNO ESCOLHEU PARA ESTUDAR',
  `nome` VARCHAR(45) NOT NULL,
  `sobrenome` VARCHAR(45) NOT NULL,
  `cpf` VARCHAR(45) NOT NULL,
  `rg` VARCHAR(45) NOT NULL,
  `orgao_exp` VARCHAR(45) NOT NULL,
  `uf_rg` VARCHAR(45) NOT NULL,
  `data_nascimento` VARCHAR(45) NOT NULL,
  `sexo` VARCHAR(45) NOT NULL,
  `celular` VARCHAR(45) NOT NULL,
  `residencial` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `cep` VARCHAR(45) NOT NULL,
  `endereco` VARCHAR(45) NOT NULL,
  `bairro` VARCHAR(45) NOT NULL,
  `cidade` VARCHAR(45) NOT NULL,
  `estado` VARCHAR(45) NOT NULL,
  `ativo` VARCHAR(45) NOT NULL,
  `pai` VARCHAR(45) NOT NULL,
  `mae` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_aluno`),
  INDEX `fk_TB_PESSOA_has_TB_TURMA_TB_TURMA1_idx` (`turma_id` ASC),
  UNIQUE INDEX `cpf_UNIQUE` (`cpf` ASC),
  CONSTRAINT `fk_TB_PESSOA_has_TB_TURMA_TB_TURMA1`
    FOREIGN KEY (`turma_id`)
    REFERENCES `DB_INVICTO`.`TB_TURMA` (`id_turma`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DB_INVICTO`.`TB_CONTRATO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DB_INVICTO`.`TB_CONTRATO` (
  `id_contrato` BIGINT NOT NULL AUTO_INCREMENT,
  `taxa_matricula` DOUBLE NOT NULL,
  `valor_curso` DOUBLE NOT NULL,
  `desconto_curso` DOUBLE(3,2) NOT NULL COMMENT 'DE O AT� 100 %',
  `qtd_parcelas_curso` TINYINT(4) NOT NULL COMMENT '1 =  AVISTA, x = NUMERO DE PARCELAS \nobs ACIMA DE 2 J� � PARCELADO',
  `valor_material` DOUBLE NOT NULL,
  `qtd_parcelas_material` TINYINT(4) NOT NULL COMMENT '1 =  AVISTA, x = NUMERO DE PARCELAS \nobs ACIMA DE 2 J� � PARCELADO',
  `dia_vencimento` TINYINT NOT NULL COMMENT 'APENAS O DIA\n1,5,10 etc.',
  `forma_pagamento` VARCHAR(45) NOT NULL COMMENT '1= DINHEIRO, 2= CARTAO-CREDITO, 3= CARTAO-DEBITO, 4= CHEQUE',
  `data_matricula` DATE NOT NULL,
  `situacao_matricula` VARCHAR(45) NOT NULL COMMENT '0 = PROCESSO DE MATR�CULA, 1= ATIVO/MATRICULADO, 2 = CANCELADO, 3 =  ACORDO , 4 = ENCERRADO',
  `matricula` VARCHAR(45) NOT NULL,
  `aluno_id` BIGINT NOT NULL,
  `condicao_contrato` VARCHAR(45) NOT NULL COMMENT 'CURSO E MATERIAL �VISTA',
  PRIMARY KEY (`id_contrato`),
  INDEX `fk_TB_CONTRATO_TB_ALUNO1_idx` (`aluno_id` ASC),
  CONSTRAINT `fk_TB_CONTRATO_TB_ALUNO1`
    FOREIGN KEY (`aluno_id`)
    REFERENCES `DB_INVICTO`.`TB_ALUNO` (`id_aluno`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
ROW_FORMAT = REDUNDANT;


-- -----------------------------------------------------
-- Table `DB_INVICTO`.`TB_PARCELA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DB_INVICTO`.`TB_PARCELA` (
  `id_parcela` BIGINT NOT NULL AUTO_INCREMENT,
  `contrato_id` BIGINT NOT NULL,
  `numero_parcela_curso` TINYINT NOT NULL COMMENT 'PARCELA DE NUMERO 1 PARCELA DE NUMERO 2 E ETC..',
  `numero_parcela_material` TINYINT NOT NULL COMMENT '\'PARCELA DE NUMERO 1 PARCELA DE NUMERO 2 E ETC..\'',
  `data_vencimento` DATE NOT NULL,
  `valor_pago` DOUBLE NOT NULL COMMENT '\'VALOR PAGO NESTA DATA\'',
  `data_pagamento` DATE NULL,
  `valor_parcela_curso` DOUBLE NOT NULL COMMENT 'VALOR COM DESCONTO E PARCELADO',
  `valor_parcela_material` DOUBLE NOT NULL COMMENT 'VALOR PARCELADO',
  `valor_total_parcela` DOUBLE NOT NULL COMMENT 'PARCELA_CURSO+PARCELA_MATERIAL+TAXA_MATRICULA',
  `situacao_parcela` VARCHAR(45) NOT NULL COMMENT '1= PAGO, 2 = A VENCER, 3 = ATRASADO 4 = CANCELADO POR ACORDO',
  `numero_parcela` TINYINT NOT NULL COMMENT 'a primeira parcela contem a 1� parcela de curso + 0� parcela de Material. Na segunda parcela contem a 2� parcela de curso + 1� parcela de Material. Na terceira parcela contem a 3� parcela de curso + 2� parcela de Material e etc..',
  `taxa_matricula` DOUBLE NOT NULL,
  `valor_residual_curso` DOUBLE NOT NULL,
  `valor_residual_material` DOUBLE NOT NULL,
  PRIMARY KEY (`id_parcela`),
  INDEX `fk_PARCELAS_MATRICULA1_idx` (`contrato_id` ASC),
  CONSTRAINT `fk_PARCELAS_MATRICULA1`
    FOREIGN KEY (`contrato_id`)
    REFERENCES `DB_INVICTO`.`TB_CONTRATO` (`id_contrato`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DB_INVICTO`.`TB_FUNCIONARIO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DB_INVICTO`.`TB_FUNCIONARIO` (
  `id_funcionario` BIGINT NOT NULL AUTO_INCREMENT,
  `matricula` VARCHAR(45) NOT NULL,
  `situacao` VARCHAR(45) NOT NULL COMMENT '1 = CONTRATADO, 2 = DEMITIDO, 3 = APOSENTADO, 4= NAO TRABALHA MAIS/PEDIU DEMISS�O',
  `cargo` VARCHAR(45) NOT NULL COMMENT '0= n�o � professor, 1= professor',
  `nome` VARCHAR(45) NOT NULL,
  `sobrenome` VARCHAR(45) NOT NULL,
  `cpf` VARCHAR(45) NOT NULL,
  `rg` VARCHAR(45) NOT NULL,
  `orgao_exp` VARCHAR(45) NOT NULL,
  `uf_rg` VARCHAR(45) NOT NULL,
  `data_nascimento` VARCHAR(45) NOT NULL,
  `sexo` VARCHAR(45) NOT NULL,
  `celular` VARCHAR(45) NOT NULL,
  `residencial` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `cep` VARCHAR(45) NOT NULL,
  `endereco` VARCHAR(45) NOT NULL,
  `bairro` VARCHAR(45) NOT NULL,
  `cidade` VARCHAR(45) NOT NULL,
  `estado` VARCHAR(45) NOT NULL,
  `ativo` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_funcionario`),
  UNIQUE INDEX `matricula_UNIQUE` (`matricula` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DB_INVICTO`.`TB_CATEGORIA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DB_INVICTO`.`TB_CATEGORIA` (
  `id_categoria` BIGINT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `tipo_categoria` TINYINT NOT NULL DEFAULT 0 COMMENT '0 = indefinida, 1 = FUNCIONARIO, 2 = ESSENCIAL, 3 = ESCRITORIO, 4 = TERCEIROS',
  `descricao` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_categoria`),
  UNIQUE INDEX `NOME_UNIQUE` (`nome` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DB_INVICTO`.`TB_DESPESA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DB_INVICTO`.`TB_DESPESA` (
  `id_despesa` BIGINT NOT NULL AUTO_INCREMENT,
  `valor` DOUBLE NOT NULL DEFAULT 0.00 COMMENT 'VALOR PREVISTO',
  `data_vencimento` DATE NOT NULL DEFAULT '2019-01-01' COMMENT 'VENCIMENTO PREVISTO',
  `ativa` TINYINT NOT NULL DEFAULT 1 COMMENT 'USADO PARA EXCLUS�O L�GICA \n0 = desativado, 1 = ativo, ',
  `via_recebido` VARCHAR(45) NOT NULL DEFAULT 'forma de recebimento' COMMENT '??',
  `funcionario_id` BIGINT NOT NULL,
  `categoria_id` BIGINT NOT NULL,
  PRIMARY KEY (`id_despesa`),
  INDEX `fk_DESPESA_FUNCIONARIO1_idx` (`funcionario_id` ASC),
  INDEX `fk_TB_DESPESA_TB_CATEGORIA1_idx` (`categoria_id` ASC),
  CONSTRAINT `fk_DESPESA_FUNCIONARIO1`
    FOREIGN KEY (`funcionario_id`)
    REFERENCES `DB_INVICTO`.`TB_FUNCIONARIO` (`id_funcionario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_TB_DESPESA_TB_CATEGORIA1`
    FOREIGN KEY (`categoria_id`)
    REFERENCES `DB_INVICTO`.`TB_CATEGORIA` (`id_categoria`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DB_INVICTO`.`TB_LANCAMENTO_DESPESA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DB_INVICTO`.`TB_LANCAMENTO_DESPESA` (
  `id_lancamento_despesa` BIGINT NOT NULL AUTO_INCREMENT,
  `valor_pago` DOUBLE NOT NULL DEFAULT 0.00,
  `data_pagamento` DATE NOT NULL DEFAULT '2019-01-01',
  `despesa_id` BIGINT NOT NULL,
  PRIMARY KEY (`id_lancamento_despesa`),
  UNIQUE INDEX `data_pagamento_UNIQUE` (`data_pagamento` ASC),
  INDEX `fk_LANCAMENTO_DESPESA_DESPESA1_idx` (`despesa_id` ASC),
  CONSTRAINT `fk_LANCAMENTO_DESPESA_DESPESA1`
    FOREIGN KEY (`despesa_id`)
    REFERENCES `DB_INVICTO`.`TB_DESPESA` (`id_despesa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
