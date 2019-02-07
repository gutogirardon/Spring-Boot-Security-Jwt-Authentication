package com.springcommerceapi.SpringCommerceAPI.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.springcommerceapi.SpringCommerceAPI.model.Cliente;

@Transactional
public interface ClienteRepository extends CrudRepository<Cliente, Long>{
	
	Optional<Cliente> findById(Long id);
	public Cliente findByNomeIgnoreCase(String nome);
	
	
}