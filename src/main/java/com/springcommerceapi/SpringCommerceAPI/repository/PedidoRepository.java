package com.springcommerceapi.SpringCommerceAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springcommerceapi.SpringCommerceAPI.model.Pedido;

import javax.transaction.Transactional;

@Transactional
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	
	
	
	
}