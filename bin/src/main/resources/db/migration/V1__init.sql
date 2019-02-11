CREATE TABLE usuario (
   id SERIAL,
   password    text NOT NULL,
   username    varchar(15) NOT NULL,
   PRIMARY KEY (id)
);

CREATE TABLE role (
   id SERIAL,
   description    varchar(20) NOT NULL,
   name    varchar(25) NOT NULL, 
   PRIMARY KEY (id)
);

CREATE TABLE user_roles (
   role_id INTEGER NOT NULL,
   user_id INTEGER NOT NULL,
   FOREIGN KEY (role_id) REFERENCES role (id),
   FOREIGN KEY (user_id) REFERENCES usuario (id)   
);