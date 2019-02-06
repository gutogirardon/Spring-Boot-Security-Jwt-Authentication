package com.springcommerceapi.SpringCommerceAPI.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springcommerceapi.SpringCommerceAPI.model.Cliente;
import com.springcommerceapi.SpringCommerceAPI.service.ClienteService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/clientes")
public class ClienteResource {

	@Autowired
	ClienteService clienteService;
	public ClienteResource(ClienteService clienteService) {
		this.clienteService = clienteService;
	}
	
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@PostMapping(value =  "/cadastrarcliente",  produces = MediaType.APPLICATION_JSON_VALUE)
	public String cadastrarCliente(@RequestBody Cliente cliente) {
		clienteService.salvarCliente(cliente);
		return "Cliente Cadastrado";
	}

	public Cliente recuperarCliente() {
		return null;
	}

	public List<Cliente> recuperarTodosClientes() {
		return null;
	}

	
	@PutMapping(value = "/atualizarcliente/{id}/", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Cliente atualizarCliente(@PathVariable Long id, Cliente cliente){
		return clienteService.alterarCliente(id, cliente);
		
	}

	public Cliente deletarCliente() {
		return null;
	}

}