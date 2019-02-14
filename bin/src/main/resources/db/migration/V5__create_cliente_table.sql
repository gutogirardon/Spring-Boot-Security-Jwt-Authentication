CREATE TABLE cliente (
   id BIGSERIAL,
   nome varchar(20) NOT NULL,
   sobrenome varchar(20) NOT NULL,
   data_nascimento timestamp,
   telefone bigserial,
   cpf bigserial,
   sexo varchar(10),
   data_registro timestamp,
   dataRegistro date,
   endereco varchar(20),
   numero varchar(10),
   bairro varchar(15),
   cep integer,
   complemento varchar(20),
   PRIMARY KEY (id)
);