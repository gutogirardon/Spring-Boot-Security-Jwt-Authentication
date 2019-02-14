package com.springcommerceapi.SpringCommerceAPI.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springcommerceapi.SpringCommerceAPI.model.Cliente;
import com.springcommerceapi.SpringCommerceAPI.model.Usuario;
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
	
//	public Cliente recuperarClienteNome(String nome) {
//		return clienteRepository.findByNomeIgnoreCase(nome);
//	}

	public Cliente recuperarClienteId(Long id) {
		return clienteRepository.findById(id).orElse(new Cliente());
	}
	
}