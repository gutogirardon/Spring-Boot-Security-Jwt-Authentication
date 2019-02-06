package com.springcommerceapi.SpringCommerceAPI.service;

import com.springcommerceapi.SpringCommerceAPI.model.Usuario;
import com.springcommerceapi.SpringCommerceAPI.model.UserDto;

import java.util.List;

public interface UsuarioService {

    Usuario save(UserDto user);
    List<Usuario> findAll();
    void delete(int id);
    Usuario findOne(String username);

    Usuario findById(int id);
}