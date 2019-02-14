CREATE TABLE estoque (
   id BIGSERIAL,
   data timestamp not null,
   tipo integer not null,
   quantidade integer not null,
   produto_id bigserial not null,
   FOREIGN KEY (produto_id) REFERENCES produto (id),
   PRIMARY KEY (id)
);