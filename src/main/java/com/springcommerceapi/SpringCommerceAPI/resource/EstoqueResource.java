package com.springcommerceapi.SpringCommerceAPI.resource;

import com.springcommerceapi.SpringCommerceAPI.model.Cliente;
import com.springcommerceapi.SpringCommerceAPI.model.Estoque;
import com.springcommerceapi.SpringCommerceAPI.model.Produto;
import com.springcommerceapi.SpringCommerceAPI.service.EstoqueService;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/relatorio")
public class EstoqueResource {
	
	@Autowired
	EstoqueService estoqueService;

	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/entrada/{dataInicio}/{dataFinal}")
	@ResponseBody
	public List<Estoque> relatorioEntrada(@PathVariable String dataInicio, @PathVariable String dataFinal) throws ParseException {
		return estoqueService.relatorioDeEntrada(dataInicio, dataFinal);

	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/saida/{dataInicio}/{dataFinal}")
	@ResponseBody
	public List<Estoque> relatorioSaida(@PathVariable String dataInicio, @PathVariable String dataFinal) throws ParseException {
		return estoqueService.relatorioDeSaida(dataInicio, dataFinal);
	}


}
