package com.springcommerceapi.SpringCommerceAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springcommerceapi.SpringCommerceAPI.model.Estoque;
import com.springcommerceapi.SpringCommerceAPI.model.Produto;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {
	Estoque findByProdutoId(long id);	
	Estoque deleteByProdutoId(long id);
}