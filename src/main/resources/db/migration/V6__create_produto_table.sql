CREATE TABLE produto (
   id BIGSERIAL,
   descricao varchar(20),
   nome varchar(25) NOT NULL,
   quantidade integer NOT NULL,
   valor double precision NOT NULL,   
   PRIMARY KEY (id)
);