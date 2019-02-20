package com.springcommerceapi.SpringCommerceAPI.resource;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springcommerceapi.SpringCommerceAPI.model.Cliente;
import com.springcommerceapi.SpringCommerceAPI.repository.ClienteRepository;
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

	@Autowired
	ClienteRepository clienteRepository;

	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@PostMapping(value =  "/cadastrarcliente",  produces = MediaType.APPLICATION_JSON_VALUE)
	public Cliente cadastrarCliente(@RequestBody Cliente cliente) {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		cliente.setDataRegistro(dateFormat.format(date));
		clienteService.salvarCliente(cliente);
		return cliente;
	}

	// Retornar dados do cliente por id
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@RequestMapping(value = "/buscar/{id}", method = RequestMethod.GET)
	public Cliente getOne(@PathVariable(value = "id") long id) {
		return clienteRepository.findById(id);
	}

	// Retornar todos os clientes
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public List<Cliente> listUser() {
		return clienteRepository.findAll();
	}

	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@RequestMapping(value = "/atualizarcliente", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Cliente atualizarCliente(@RequestBody Cliente cliente){
		clienteService.alterarCliente(cliente);
		return cliente;
	}

	// Deletar o cliente passando o id
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@RequestMapping(value = "/deletar", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> delete_user(@RequestParam(value = "id") long id) {
		try{
		if (clienteService.deletarCliente(id) == true) {
			return new ResponseEntity<String>("Usuário Deletado com Sucesso", HttpStatus.OK);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não pode ser deletado");
		}
	}catch(NullPointerException npe){
		  
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não pode ser deletado");
	}
}
