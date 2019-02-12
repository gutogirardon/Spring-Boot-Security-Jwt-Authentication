CREATE TABLE pedido (
   id BIGSERIAL,
   data_pedido timestamp not null,
   valor_total double precision NOT NULL,
   status integer,
   cliente_id bigserial NOT NULL,
   FOREIGN KEY (cliente_id) REFERENCES cliente (id),   
   PRIMARY KEY (id)
);