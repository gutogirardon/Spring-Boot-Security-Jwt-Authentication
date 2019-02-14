package com.springcommerceapi.SpringCommerceAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springcommerceapi.SpringCommerceAPI.model.Estoque;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {
}