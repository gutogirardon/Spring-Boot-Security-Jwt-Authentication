package com.springcommerceapi.SpringCommerceAPI.service.impl;

import com.springcommerceapi.SpringCommerceAPI.repository.UsuarioRepository;
import com.springcommerceapi.SpringCommerceAPI.model.Usuario;
import com.springcommerceapi.SpringCommerceAPI.model.UserDto;
import com.springcommerceapi.SpringCommerceAPI.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;


@Service(value = "usuarioService")
public class UserServiceImpl implements UserDetailsService, UsuarioService {
    
    @Autowired
    private UsuarioRepository userDao;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = userDao.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("Login ou senha invalidos.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
    }

    private Set<SimpleGrantedAuthority> getAuthority(Usuario user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> {
            //authorities.add(new SimpleGrantedAuthority(role.getName()));
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        });
        return authorities;
        //return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    public List<Usuario> findAll() {
        List<Usuario> list = new ArrayList<>();
        userDao.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public void delete(int id) {
        userDao.deleteById(id);
    }

    @Override
    public Usuario findOne(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public Usuario findById(int id) {
        return userDao.findById(id).get();
    }

    @Override
    public Usuario save(UserDto user) {
        Usuario newUser = new Usuario();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        return userDao.save(newUser);
    }
}