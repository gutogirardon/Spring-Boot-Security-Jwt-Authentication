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

	@Autowired
	PedidoRepository pedidoRepository;

	public void salvarItens(Long idPedido, List<ItemPedido> item) {
		Pedido pedido = pedidoRepository.findById(idPedido).orElse(null);
		for (ItemPedido itemPedido : item) {
			Produto produto = produtoRepository.findById(itemPedido.getProduto().getId()).orElse(null);
			if (verificarDisponibilidade(itemPedido.getProduto().getId(), itemPedido.getQuantidade()) == true) {
				produto.setQuantidade(produto.getQuantidade() - itemPedido.getQuantidade());
				itemPedido.setPreco_unitario(produto.getValor());
				itemPedido.setPreco_total(produto.getValor() * itemPedido.getQuantidade());
				itemPedido.setPedido(pedido);
				itemPedido.setProduto(produto);
				pedido.setValor_total(pedido.getValor_total() + itemPedido.getPreco_total());
				atualizarEstoqueSaida(itemPedido, produto);
				pedidoRepository.save(pedido);
				itemPedidoRepository.save(itemPedido);
			} else {
				//implementar o que acontece caso não tenha a quantidade do produto
			}
		}
	}

	private boolean verificarDisponibilidade(Long idProduto, int quantidade) {
		Produto produto = produtoRepository.findById(idProduto).orElse(null);
		if (quantidade <= produto.getQuantidade() && quantidade > 0) {
			return true;
		}
		return false;
	}

	public void atualizarEstoqueSaida(ItemPedido itemPedido, Produto produto) {
		Estoque estoque = new Estoque(2, itemPedido.getQuantidade(), produto);
		estoqueRepository.save(estoque);
	}

	public void atualizarEstoqueEntrada(Pedido pedido) {
		ArrayList<ItemPedido> item = (ArrayList<ItemPedido>) pedido.getItensPedido();
		for (ItemPedido itemPedido : item) {
			Produto produto = produtoRepository.findById(itemPedido.getProduto().getId()).orElse(null);
				produto.setQuantidade(produto.getQuantidade() + itemPedido.getQuantidade());
				Estoque estoque = new Estoque(1, itemPedido.getQuantidade(), produto);
				produtoRepository.save(produto);
				estoqueRepository.save(estoque);
			}
		}
}
