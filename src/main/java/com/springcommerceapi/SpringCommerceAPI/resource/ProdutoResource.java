package com.springcommerceapi.SpringCommerceAPI.resource;
import com.springcommerceapi.SpringCommerceAPI.model.Produto;
import com.springcommerceapi.SpringCommerceAPI.service.ProdutoService;
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

	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@PostMapping(value = "/cadastrar", produces = MediaType.APPLICATION_JSON_VALUE)
	public String cadastraProduto(@RequestBody Produto produto){
		produtoService.salvarProduto(produto, 1, produto.getQuantidade());
		return "Produto Salvo";
	}

	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@DeleteMapping(value = "/deletar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)	
	public String deletarProduto(@PathVariable Long id){
		return produtoService.deletarProduto(id);
	}

	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@GetMapping(value = "/buscarproduto/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Produto buscarProdutoId(@PathVariable Long id){
	    return produtoService.buscarProdutoId(id);
	            
	}

//	@PreAuthorize("hasAnyRole('ADMIN','USER')")
//	@GetMapping(value = "/buscarproduto/", produces = MediaType.APPLICATION_JSON_VALUE)
//	@ResponseBody
//	public Produto buscarProdutoNome(@RequestParam(value="nome") String nome){
//		return produtoService.buscarProdutoNome(nome);
//	}

	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@RequestMapping(value = "/alterarproduto/", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Produto atualizarProduto (@RequestBody Produto produto){
        produtoService.alterarProduto(produto);
	    return null;
    }

}