CREATE TABLE pessoa (
                        idPessoa   INT AUTO_INCREMENT PRIMARY KEY,
                        nomePessoa VARCHAR(255) NOT NULL,
                        endereco   VARCHAR(255) NULL,
                        uf         VARCHAR(2)   NULL,
                        telefone   VARCHAR(20)  NULL,
                        cpf        VARCHAR(14)  NULL,
                        email      VARCHAR(255) NULL,
                        tipo       CHAR(20)     NULL
);

CREATE TABLE disciplina (
                            codigo         INT AUTO_INCREMENT PRIMARY KEY,
                            nomeDisciplina VARCHAR(255) NOT NULL,
                            cargaHoraria   INT          NULL,
                            professor      INT,
                            limiteAlunos   INT          NULL,
                            CONSTRAINT professor_fk FOREIGN KEY (professor) REFERENCES pessoa (idPessoa),
                            CHECK (limiteAlunos >= 0) -- Garante que o limite de alunos seja não negativo
);

CREATE TABLE matricula (
                           idmat         INT AUTO_INCREMENT PRIMARY KEY,
                           disciplina    INT,
                           dataMatricula DATE,
                           valorPago     DECIMAL(10, 2),
                           aluno         INT,
                           periodo       VARCHAR(255) NULL,
                           CONSTRAINT disciplina_fk FOREIGN KEY (disciplina) REFERENCES disciplina (codigo),
                           CONSTRAINT aluno_fk FOREIGN KEY (aluno) REFERENCES pessoa (idPessoa),
                           CHECK (valorPago >= 0) -- Garante que o valor pago seja não negativo
);

CREATE TABLE usuario (
                         id    INT AUTO_INCREMENT PRIMARY KEY,
                         nome  VARCHAR(255) NOT NULL,
                         cargo VARCHAR(255) NULL,
                         login VARCHAR(255) NOT NULL,
                         senha VARCHAR(255) NOT NULL,
                         email VARCHAR(255) NOT NULL,
                         UNIQUE (email),
                         UNIQUE (login)
);
