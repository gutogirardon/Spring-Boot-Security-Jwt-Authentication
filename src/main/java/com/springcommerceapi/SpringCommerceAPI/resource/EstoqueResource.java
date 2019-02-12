package com.springcommerceapi.SpringCommerceAPI.resource;

import com.springcommerceapi.SpringCommerceAPI.model.Cliente;
import com.springcommerceapi.SpringCommerceAPI.model.Produto;
import com.springcommerceapi.SpringCommerceAPI.service.EstoqueService;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@GetMapping("/entrada/{dataInicio}/{dataFinal}")
	@ResponseBody
	public String relatorioEntrada(@PathVariable String dataInicio, @PathVariable String dataFinal) throws ParseException {
		return estoqueService.relatorioDeEntrada(dataInicio, dataFinal) + "";

	}
	
	@GetMapping("/saida/{dataInicio}/{dataFinal}")
	@ResponseBody
	public String relatorioSaida(@PathVariable String dataInicio, @PathVariable String dataFinal) throws ParseException {
		return estoqueService.relatorioDeSaida(dataInicio, dataFinal) + "";

	}

	public List<Produto> gerarRelatorioSaidaProdutos() {
		return null;
	}

	public List<Produto> gerarRelatorioEntradaProdutos() {
		return null;
	}

}
