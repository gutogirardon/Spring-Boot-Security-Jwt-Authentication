package com.springcommerceapi.SpringCommerceAPI.resource;

import com.springcommerceapi.SpringCommerceAPI.model.Produto;
import com.springcommerceapi.SpringCommerceAPI.repository.ProdutoRepository;
import com.springcommerceapi.SpringCommerceAPI.service.ProdutoService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/produtos")
public class ProdutoResource {

	@Autowired
	ProdutoService produtoService;

	public ProdutoResource(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}

	@Autowired
	ProdutoRepository produtoRepository;

	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@PostMapping(value = "/cadastrar", produces = MediaType.APPLICATION_JSON_VALUE)
	public Produto cadastraProduto(@RequestBody Produto produto){
		produtoService.salvarProduto(produto, 1);
		return produto;
	}

	// Retornar dados do produto por id
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@RequestMapping(value = "/buscar/{id}", method = RequestMethod.GET)
	public Optional<Produto> getOne(@PathVariable(value = "id") long id) {
		return produtoRepository.findById(id);
	}

	// Retornar todos os produtos cadastrados
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public List<Produto> listUser() {
		return produtoRepository.findAll();
	}

	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@RequestMapping(value = "/atualizarproduto", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Produto atualizarProduto (@RequestBody Produto produto){
		produtoService.alterarProduto(produto);
		return produto;


	}

	// Deletar o produto passando o id
		@PreAuthorize("hasAnyRole('ADMIN','USER')")
		@RequestMapping(value = "/deletar", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody String delete_produto(@RequestParam(value = "id") long id) {
			produtoService.deletarProduto(id);
			return "Produto Deletado com Sucesso";
			
		}

}