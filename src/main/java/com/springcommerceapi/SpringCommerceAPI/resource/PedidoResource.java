package com.springcommerceapi.SpringCommerceAPI.resource;

import com.springcommerceapi.SpringCommerceAPI.model.ItemPedido;
import com.springcommerceapi.SpringCommerceAPI.service.ItemPedidoService;
import com.springcommerceapi.SpringCommerceAPI.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.springcommerceapi.SpringCommerceAPI.model.Cliente;
import com.springcommerceapi.SpringCommerceAPI.model.Pedido;
import com.springcommerceapi.SpringCommerceAPI.model.Produto;
import com.springcommerceapi.SpringCommerceAPI.repository.ClienteRepository;
import com.springcommerceapi.SpringCommerceAPI.repository.PedidoRepository;
import com.springcommerceapi.SpringCommerceAPI.repository.ProdutoRepository;
import com.springcommerceapi.SpringCommerceAPI.service.ProdutoService;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/pedidos")
public class PedidoResource {

    @Autowired
    ItemPedidoService itemPedidoService;

    @Autowired
    PedidoService pedidoService;

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping(value =  "/criar/{idCliente}")
    public Pedido criarPedido(@PathVariable Long idCliente) {
        Pedido pedido = pedidoService.salvarPedido(idCliente);
        return pedido;
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping(value = "/adicionar/itens/{idPedido}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<ItemPedido> cadastrarPedido(@PathVariable Long idPedido, @RequestBody List<ItemPedido> itemPedido){
        itemPedidoService.salvarItens(idPedido, itemPedido);
        return itemPedido;
    }
}
