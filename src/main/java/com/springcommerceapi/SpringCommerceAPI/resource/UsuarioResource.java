package com.springcommerceapi.SpringCommerceAPI.resource;

import com.springcommerceapi.SpringCommerceAPI.model.Usuario;
import com.springcommerceapi.SpringCommerceAPI.model.UserDto;
import com.springcommerceapi.SpringCommerceAPI.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UsuarioResource {

    @Autowired
    private UsuarioService usuarioService;

    //@Secured({"ROLE_ADMIN", "ROLE_USER"})
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value="/usuarios", method = RequestMethod.GET)
    public List<Usuario> listUser(){
        return usuarioService.findAll();
    }

    //@Secured("ROLE_USER")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    ////@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @RequestMapping(value = "/usuarios/{id}", method = RequestMethod.GET)
    public Usuario getOne(@PathVariable(value = "id") int id){
        return usuarioService.findById(id);
    }

    @RequestMapping(value="/registrar", method = RequestMethod.POST)
    public Usuario saveUser(@RequestBody UserDto user){
        return usuarioService.save(user);
    }



}