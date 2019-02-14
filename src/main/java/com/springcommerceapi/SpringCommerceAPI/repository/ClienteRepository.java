package com.springcommerceapi.SpringCommerceAPI.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.springcommerceapi.SpringCommerceAPI.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Cliente findByNome(String nome);
    Cliente findById(long id);
    List<Cliente> findAll();    	

}