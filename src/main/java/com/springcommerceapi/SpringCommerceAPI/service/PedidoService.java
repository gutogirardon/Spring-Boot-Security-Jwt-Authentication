package com.springcommerceapi.SpringCommerceAPI.service;

import com.springcommerceapi.SpringCommerceAPI.model.Cliente;
import com.springcommerceapi.SpringCommerceAPI.model.ItemPedido;
import com.springcommerceapi.SpringCommerceAPI.model.Pedido;
import com.springcommerceapi.SpringCommerceAPI.repository.ClienteRepository;
import com.springcommerceapi.SpringCommerceAPI.repository.ItemPedidoRepository;
import com.springcommerceapi.SpringCommerceAPI.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

	@Autowired
	PedidoRepository pedidoRepository;

	@Autowired
	ClienteRepository clienteRepository;

	public PedidoService(PedidoRepository pedidoRepository) {
		this.pedidoRepository = pedidoRepository;
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

		for(Pedido x: pedidos){
			Date escopo = null;

			String umaDta = new String(x.getData_pedido().toString());
			escopo = format.parse(umaDta);

			if ((escopo.compareTo(dataI) == 1 || escopo.compareTo(dataI) == 0) && (escopo.compareTo(dataF) == 0 ||
					escopo.compareTo(dataF) == -1)){
				relatorioPedidos.add(x);
			}

		}
		return relatorioPedidos;
	}

	public Pedido salvarPedido(Long idCliente){
		Cliente cliente = clienteRepository.findById(idCliente).orElse(null);
		Pedido pedido = new Pedido(cliente);
		pedidoRepository.save(pedido);
		return pedido;
	}
	
}
