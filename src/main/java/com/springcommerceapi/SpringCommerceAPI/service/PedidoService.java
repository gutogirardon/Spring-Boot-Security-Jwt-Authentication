package com.springcommerceapi.SpringCommerceAPI.service;

import com.springcommerceapi.SpringCommerceAPI.model.Cliente;
import com.springcommerceapi.SpringCommerceAPI.model.ItemPedido;
import com.springcommerceapi.SpringCommerceAPI.model.Pedido;
import com.springcommerceapi.SpringCommerceAPI.model.ProductNotFoundException;
import com.springcommerceapi.SpringCommerceAPI.model.Produto;
import com.springcommerceapi.SpringCommerceAPI.repository.ClienteRepository;
import com.springcommerceapi.SpringCommerceAPI.repository.PedidoRepository;
import com.springcommerceapi.SpringCommerceAPI.repository.ProdutoRepository;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

	@Autowired
	PedidoRepository pedidoRepository;

	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	ProdutoRepository produtoRepository;

	@Autowired
	ItemPedidoService itemPedidoService;

	public PedidoService(PedidoRepository pedidoRepository) {
		this.pedidoRepository = pedidoRepository;
	}

	public Pedido salvarPedido(Pedido pedido) {
		pedido.setData_pedido(new Date());
		Cliente cliente = clienteRepository.findById(pedido.getCliente().getId()).orElse(null);
		
		if (clienteRepository.existsById(pedido.getCliente().getId()) == false) {
			throw new ProductNotFoundException("Cliente não encontrado");
		} else {
		
		pedido.setCliente(cliente);
		Iterator<ItemPedido> it = pedido.getItensPedido().iterator();
		while (it.hasNext()) {
			ItemPedido itemPedido = it.next();
			Produto produto = produtoRepository.findById(itemPedido.getProduto().getId()).orElse(null);
			if (itemPedidoService.verificarDisponibilidade(itemPedido.getProduto().getId(),
					itemPedido.getQuantidade()) == true) {
				produto.setQuantidade(produto.getQuantidade() - itemPedido.getQuantidade());
				itemPedido.setPreco_unitario(produto.getValor());
				itemPedido.setPreco_total(produto.getValor() * itemPedido.getQuantidade());
				itemPedido.setPedido(pedido);
				itemPedido.setProduto(produto);
				pedido.setValor_total(pedido.getValor_total() + itemPedido.getPreco_total());
				itemPedidoService.atualizarEstoqueSaida(itemPedido, produto);
			} else {
				it.remove();
				throw new ProductNotFoundException("Produto não disponível em estoque");
			}
		}
		if (!pedido.getItensPedido().isEmpty()) {
			pedidoRepository.save(pedido);
		}
		return pedido;
	}
}

	public Pedido alterarPedido(Long idPedido) {
		Pedido pedido = pedidoRepository.findById(idPedido).orElse(null);
		if (pedido != null) {
			if (pedido.getStatus() == 0) {
				pedido.setStatus(1);
				itemPedidoService.atualizarEstoqueEntrada(pedido);
				pedidoRepository.save(pedido);
			} else {
				throw new ProductNotFoundException("Pedido já cancelado");
			}
		}
		return pedido;
	}

	public List<Pedido> relatorio(String dataInicio, String dataFinal) throws ParseException {
		Date dataI = null;
		Date dataF = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String dataTextoAtual = new String(dataInicio);
		String dataTextoAntiga = new String(dataFinal);
		format.setLenient(false);
		dataI = format.parse(dataTextoAtual);
		dataF = format.parse(dataTextoAntiga);

		List<Pedido> relatorioPedidos = new ArrayList<>();
		List<Pedido> pedidos = pedidoRepository.findAll();

		for (Pedido x : pedidos) {
			Date escopo = null;

			String umaDta = new String(x.getData_pedido().toString());
			escopo = format.parse(umaDta);

			if ((escopo.compareTo(dataI) == 1 || escopo.compareTo(dataI) == 0)
					&& (escopo.compareTo(dataF) == 0 || escopo.compareTo(dataF) == -1)) {
				relatorioPedidos.add(x);
			}

		}
		return relatorioPedidos;
	}

	public List<Pedido> relatorioPedidoCliente(Long idCliente) {

		List<Pedido> pedidos = pedidoRepository.findAll();

		List<Pedido> pedidosPorCliente = new ArrayList<>();

		for (Pedido pedido : pedidos) {
			if (pedido.getCliente().getId() == idCliente) {
				pedidosPorCliente.add(pedido);
			}
		}

		return pedidosPorCliente;
	}

	public Optional<Pedido> buscarPedido(Long idPedido) {
		Optional<Pedido> pedido = pedidoRepository.findById(idPedido);
		return pedido;
	}

}
