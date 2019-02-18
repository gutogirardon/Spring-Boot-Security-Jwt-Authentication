package com.springcommerceapi.SpringCommerceAPI.service;

import com.springcommerceapi.SpringCommerceAPI.model.Estoque;
import com.springcommerceapi.SpringCommerceAPI.model.ItemPedido;
import com.springcommerceapi.SpringCommerceAPI.model.Pedido;
import com.springcommerceapi.SpringCommerceAPI.model.Produto;
import com.springcommerceapi.SpringCommerceAPI.repository.EstoqueRepository;
import com.springcommerceapi.SpringCommerceAPI.repository.ItemPedidoRepository;
import com.springcommerceapi.SpringCommerceAPI.repository.PedidoRepository;
import com.springcommerceapi.SpringCommerceAPI.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemPedidoService {

	@Autowired
	ItemPedidoRepository itemPedidoRepository;

	@Autowired
	ProdutoRepository produtoRepository;

	@Autowired
	EstoqueRepository estoqueRepository;

	public boolean verificarDisponibilidade(Long idProduto, int quantidade) {
		Produto produto = produtoRepository.findById(idProduto).orElse(null);
		if(produto != null){
			if (quantidade <= produto.getQuantidade() && quantidade > 0) {
				return true;
			}
		}
		return false;
	}

	public void atualizarEstoqueSaida(ItemPedido itemPedido, Produto produto) {
		Estoque estoque = new Estoque(2, itemPedido.getQuantidade(), produto);
		estoqueRepository.save(estoque);
	}

	public void atualizarEstoqueEntrada(Pedido pedido) {
		List<ItemPedido> item = pedido.getItensPedido();
		for (ItemPedido itemPedido : item) {
			Produto produto = produtoRepository.findById(itemPedido.getProduto().getId()).orElse(null);
				produto.setQuantidade(produto.getQuantidade() + itemPedido.getQuantidade());
				Estoque estoque = new Estoque(1, itemPedido.getQuantidade(), produto);
				produtoRepository.save(produto);
				estoqueRepository.save(estoque);
			}
		}
}
