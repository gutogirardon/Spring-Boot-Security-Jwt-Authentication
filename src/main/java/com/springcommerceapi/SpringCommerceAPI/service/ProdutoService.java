package com.springcommerceapi.SpringCommerceAPI.service;

import com.springcommerceapi.SpringCommerceAPI.model.Estoque;
import com.springcommerceapi.SpringCommerceAPI.model.Produto;
import com.springcommerceapi.SpringCommerceAPI.repository.EstoqueRepository;
import com.springcommerceapi.SpringCommerceAPI.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;


@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository iProdutoRepository;
	
	@Autowired
	EstoqueRepository estoqueRepository;

	public ProdutoService(ProdutoRepository iProdutoRepository, EstoqueRepository estoqueRepository) {
		this.iProdutoRepository = iProdutoRepository;
		this.estoqueRepository = estoqueRepository;
	}

	//metodo para inserir no estoque quando o produto é adicionado	
	public void salvarProduto(Produto produto, int status){
		Estoque estoque = new Estoque(status, produto.getQuantidade(), produto);
		iProdutoRepository.save(produto);
		estoqueRepository.save(estoque);
	}
	
	public String deletarProduto(Long id){
	     Produto produto = iProdutoRepository.findById(id).orElse(new Produto());
	     if(produto.getId() == null){
	         return "produto não encontrado!";
         }else {
             iProdutoRepository.delete(produto);
             return "produto deletado com sucesso!";
         }
	}
	
	public Produto buscarProdutoId(Long id){
	     Produto produto = iProdutoRepository.findById(id).orElse(new Produto());
	     if(produto.getId() == null){
	         return produto;
       }else {
           return produto;
       }
	}

	public Produto buscarProdutoNome(@RequestParam(value="nome") String nome){
        Produto produto = iProdutoRepository.findByNome(nome);
	    return produto;
    }

    public Produto alterarProduto(Produto produto){
		Produto produto1 = iProdutoRepository.findById(produto.getId()).orElse(new Produto());
		produto.setId(produto1.getId());
		iProdutoRepository.save(produto);
		return null;

	}

	
	




}