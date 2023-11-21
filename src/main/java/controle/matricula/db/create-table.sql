CREATE TABLE pessoa (
    idPessoa   INT AUTO_INCREMENT PRIMARY KEY,
    nomePessoa        VARCHAR(255) NOT NULL,
    endereco          VARCHAR(255),
    uf                VARCHAR(2),
    telefone          VARCHAR(20),
    cpf               VARCHAR(14),
    email             VARCHAR(255),
    tipo              CHAR(20) CHECK (tipo IN ('professor', 'aluno'))
);

CREATE TABLE disciplina (
    codigo             INT AUTO_INCREMENT PRIMARY KEY,
    nomeDisciplina     VARCHAR(255) NOT NULL,
    cargaHoraria       INT,
    professor          INT,
    limiteAlunos       INT CHECK (limiteAlunos >= 0),
    FOREIGN KEY (professor) REFERENCES pessoa (idPessoa)
);

CREATE TABLE matricula (
    idmat               INT AUTO_INCREMENT PRIMARY KEY,
    disciplina          INT,
    dataMatricula       DATE,
    valorPago           DECIMAL(10, 2) CHECK (valorPago >= 0),
    aluno               INT,
    periodo             VARCHAR(255),
    FOREIGN KEY (aluno) REFERENCES pessoa (idPessoa),
    FOREIGN KEY (disciplina) REFERENCES disciplina (codigo)
);

CREATE TABLE usuario (
    INT                 AUTO_INCREMENT PRIMARY KEY,
    nome                VARCHAR(255) NOT NULL,
    cargo               VARCHAR(255),
    login               VARCHAR(255) NOT NULL UNIQUE,
    senha               VARCHAR(255) NOT NULL,
    email               VARCHAR(255) NOT NULL UNIQUE
);

INSERT INTO usuario (nome, cargo, login, senha, email)
VALUES ('Administrador', 'Administrador', 'admin', '123', 'admin@admin.com');

