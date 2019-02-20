package com.springcommerceapi.SpringCommerceAPI.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springcommerceapi.SpringCommerceAPI.model.Cliente;
import com.springcommerceapi.SpringCommerceAPI.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	ClienteRepository clienteRepository;
	
	
	public ClienteService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}
	
	public void salvarCliente(Cliente cliente) {
		clienteRepository.save(cliente);
	}
	
	public Cliente alterarCliente(Cliente cliente) {
		Cliente cliente1 = clienteRepository.findById(cliente.getId()).orElse(new Cliente());
		cliente.setId(cliente1.getId());
		clienteRepository.save(cliente);		
		return null;	
	}
	
	// se o cliente tem 1 pedido, produto não pode ser deletado
	public boolean deletarCliente(Long id) {
		Cliente cliente = clienteRepository.findById(id).orElse(new Cliente());
		
		if (cliente.getPedidos().isEmpty() == false) {
			return false;
		} else {
			clienteRepository.delete(cliente);
			return true;
		}
		
	}

	public Optional<Cliente> recuperarClienteId(Long id) {
		return clienteRepository.findById(id);
	}
	
}