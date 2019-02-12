CREATE TABLE item_pedido (
   id BIGSERIAL,
   preco_unitario double precision NOT NULL,
   quantidade integer not null,
   preco_total double precision NOT NULL,
   pedido_id bigserial NOT NULL,
   produto_id bigserial NOT NULL,
   FOREIGN KEY (pedido_id) REFERENCES pedido (id),
   FOREIGN KEY (produto_id) REFERENCES produto (id),   
   PRIMARY KEY (id)
);