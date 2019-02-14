package com.springcommerceapi.SpringCommerceAPI.service;

import com.springcommerceapi.SpringCommerceAPI.model.ItemPedido;
import com.springcommerceapi.SpringCommerceAPI.model.Pedido;
import com.springcommerceapi.SpringCommerceAPI.model.Produto;
import com.springcommerceapi.SpringCommerceAPI.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PedidoService {

	@Autowired
	PedidoRepository pedidoRepository;

	public PedidoService(PedidoRepository pedidoRepository) {
		this.pedidoRepository = pedidoRepository;
	}

	public List<ItemPedido> getItensPedido() {
		return null;
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
	
	
}
