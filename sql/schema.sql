CREATE TABLE PESSOA (
	CPF VARCHAR(11) NOT NULL PRIMARY KEY,
	NOME VARCHAR (100) NOT NULL
);

CREATE TABLE TELEFONE_PESSOA (
	TELEFONE VARCHAR(13) NOT NULL UNIQUE,
	CPF VARCHAR(11) NOT NULL REFERENCES PESSOA(CPF),
	PRIMARY KEY(TELEFONE, CPF)
);

CREATE TABLE FUNCIONARIO (
	CPF_GERENTE VARCHAR(11),
	CPF VARCHAR(11) NOT NULL UNIQUE REFERENCES PESSOA(CPF),
	SALARIO NUMERIC,
	RG VARCHAR(10),
	PRIMARY KEY(CPF)
);

CREATE TABLE GERENTE (
	CPF VARCHAR(11) NOT null UNIQUE,
	PRIMARY KEY(CPF),
	FOREIGN KEY(CPF) REFERENCES FUNCIONARIO(CPF)
);

CREATE TABLE CLIENTE (
	CPF VARCHAR(11) NOT NULL unique REFERENCES PESSOA(CPF),
	PONTOS_DE_FIDELIDADE INT,
	PRIMARY KEY(CPF)
);

CREATE TABLE ATENDENTE (
	CPF VARCHAR(11) NOT null UNIQUE,
	PRIMARY KEY(CPF),
	FOREIGN KEY(CPF) REFERENCES FUNCIONARIO(CPF)
);

CREATE TABLE CAIXA(
    CPF VARCHAR(11) NOT null UNIQUE,
    PRIMARY KEY (CPF),
    FOREIGN KEY (CPF) REFERENCES FUNCIONARIO (CPF)
);

CREATE TABLE TECNICO_DE_MANUTENCAO (
    CPF VARCHAR(11) NOT null UNIQUE,
    PRIMARY KEY (CPF),
    FOREIGN KEY (CPF) REFERENCES FUNCIONARIO (CPF)
);

CREATE TABLE ATENDE (
	CPF_CLIENTE VARCHAR(11) NOT NULL,
	CPF_ATENDENTE VARCHAR(10) NOT NULL,
	HORARIO_ATENDIMENTO TIMESTAMP NOT NULL,
	PRIMARY KEY(CPF_ATENDENTE, CPF_CLIENTE, HORARIO_ATENDIMENTO),
	FOREIGN KEY(CPF_ATENDENTE) REFERENCES ATENDENTE(CPF),
	FOREIGN KEY(CPF_CLIENTE) REFERENCES CLIENTE(CPF)
);

CREATE TABLE VENDA (
	CPF_CLIENTE VARCHAR(11),
	CPF_CAIXA VARCHAR(11),
	ID BIGSERIAL NOT NULL PRIMARY KEY,
	HORA TIMESTAMP UNIQUE,
	METODO_DE_PAGAMENTO VARCHAR(40),
	PONTOS_A_ATRIBUIR INT,
	DESCONTO_OBTIDO_POR_PONTOS NUMERIC,
	FOREIGN KEY(CPF_CAIXA) REFERENCES CAIXA(CPF),
	FOREIGN KEY(CPF_CLIENTE) REFERENCES CLIENTE(CPF)
);

CREATE TABLE ITEM_A_REPARAR (
	CPF_DONO VARCHAR(11) NOT NULL,
	ID_ITEM_REPARO INT NOT NULL PRIMARY KEY,
	DESCRICAO VARCHAR(150),
	FOREIGN KEY(CPF_DONO) REFERENCES CLIENTE(CPF)
);

CREATE TABLE CONSERTA (
	CPF_TECNICO VARCHAR(11) , 
    ID_ITEM_REPARO INT NOT NULL,
	VALOR_DO_CONSERTO NUMERIC NOT NULL,
	DESCONTO_POR_PONTOS NUMERIC,
	TIMESTAMP_DO_PEDIDO TIMESTAMP,
	PRIMARY KEY(CPF_TECNICO, ID_ITEM_REPARO),
	FOREIGN KEY(CPF_TECNICO) REFERENCES TECNICO_DE_MANUTENCAO(CPF),
	FOREIGN KEY(ID_ITEM_REPARO) REFERENCES ITEM_A_REPARAR(ID_ITEM_REPARO)
);

CREATE TABLE ESTOQUE (
	NOME VARCHAR (100) PRIMARY KEY
);

CREATE TABLE PRODUTO (
	NOME_ESTOQUE VARCHAR (100),
	ID_VENDA INT,
	CODIGO_DE_BARRAS VARCHAR(64) PRIMARY KEY,
	NOME VARCHAR(50) NOT NULL,
	PRECO NUMERIC NOT NULL,
	FOREIGN KEY(NOME_ESTOQUE) REFERENCES ESTOQUE(NOME),
	FOREIGN KEY(ID_VENDA) REFERENCES VENDA(ID)
);

CREATE TABLE FORNECEDOR (
	CNPJ VARCHAR(50) PRIMARY KEY,
	NOME VARCHAR(50) NOT NULL,
	ENDERECO_EMAIL VARCHAR(50)
);

CREATE TABLE TELEFONE_FORNECEDOR (
	CNPJ VARCHAR(50) REFERENCES FORNECEDOR(CNPJ),
	TELEFONE VARCHAR(16),
	PRIMARY KEY (CNPJ, TELEFONE)
);

CREATE TABLE COMPRA (
	CNPJ VARCHAR(50),
	CPF_GERENTE VARCHAR(11),
	CODIGO_DE_BARRAS VARCHAR(64) UNIQUE,
	VALOR_A_PAGAR NUMERIC NOT NULL,
	PRIMARY KEY(CNPJ, CPF_GERENTE, CODIGO_DE_BARRAS),
	FOREIGN KEY(CNPJ) REFERENCES FORNECEDOR(CNPJ),
	FOREIGN KEY(CODIGO_DE_BARRAS) REFERENCES PRODUTO(CODIGO_DE_BARRAS),
	FOREIGN KEY(CPF_GERENTE) REFERENCES GERENTE(CPF)
);

CREATE TABLE JOGOS_MIDIA_FISICA (
	CODIGO_DE_BARRAS VARCHAR(64) PRIMARY KEY REFERENCES PRODUTO(CODIGO_DE_BARRAS),
	DESENVOLVEDOR VARCHAR(50),
	FRANQUIA VARCHAR(50),
	GENERO VARCHAR(50),
	DATA_DE_LANCAMENTO DATE,
	PUBLICADORA VARCHAR(50),
	PLATAFORMA VARCHAR(50)
);

CREATE TABLE ACESSORIOS (
	CODIGO_DE_BARRAS VARCHAR(64) PRIMARY KEY REFERENCES PRODUTO(CODIGO_DE_BARRAS),
	CATEGORIA VARCHAR(50),
	GARANTIA VARCHAR(50),
	FABRICANTE VARCHAR(50)
);

CREATE TABLE CONSOLE (
	CODIGO_DE_BARRAS VARCHAR(64) PRIMARY KEY REFERENCES PRODUTO(CODIGO_DE_BARRAS),
	FABRICANTE VARCHAR(50),
	GARANTIA VARCHAR(50),
	MARCA VARCHAR(50)
);
  
ALTER TABLE FUNCIONARIO ADD FOREIGN KEY(CPF_GERENTE) REFERENCES GERENTE(CPF);
