package com.springcommerceapi.SpringCommerceAPI.service;

import com.springcommerceapi.SpringCommerceAPI.model.Produto;
import com.springcommerceapi.SpringCommerceAPI.repository.IProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class ProdutoService {

	@Autowired
	IProdutoRepository iProdutoRepository;

	public ProdutoService(IProdutoRepository iProdutoRepository) {
		this.iProdutoRepository = iProdutoRepository;
	}

	public void salvarProduto(Produto produto){
		iProdutoRepository.save(produto);
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