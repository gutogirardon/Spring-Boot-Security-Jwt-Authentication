package com.springcommerceapi.SpringCommerceAPI.repository;

import com.springcommerceapi.SpringCommerceAPI.model.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {
    Usuario findByUsername(String username);
    
}