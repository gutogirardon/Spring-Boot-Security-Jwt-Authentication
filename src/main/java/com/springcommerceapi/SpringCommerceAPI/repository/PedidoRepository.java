package com.springcommerceapi.SpringCommerceAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springcommerceapi.SpringCommerceAPI.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}