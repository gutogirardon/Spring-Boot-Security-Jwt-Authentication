package com.springcommerceapi.SpringCommerceAPI.resource;

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
    private PedidoRepository pedidoRepository;
    
    @Autowired
	ProdutoService produtoService;

    @Autowired
    PedidoService pedidoService;
    
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
	@PostMapping(value =  "/comprar/{idCliente}/{idProduto}/{quantidade}")
    public String comprar(@PathVariable Long idCliente, @PathVariable Long idProduto, @PathVariable Integer quantidade) {
        Cliente cliente = clienteRepository.findById(idCliente).orElse(null);
        Produto produto = produtoRepository.findById(idProduto).orElse(null);
    	
        Pedido pedido = new Pedido(cliente);
        pedido.comprar(produto, quantidade);

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

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/relatorio/{dataInicio}/{dataFinal}")
    @ResponseBody
    public String relatorioPedidos(@PathVariable String dataInicio, @PathVariable String dataFinal) throws ParseException {
        return pedidoService.relatorio(dataInicio, dataFinal) + "";

    }
    
    

    //implementar método que retorna os pedidos por cliente
    /*@PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping(value = "/{idCliente}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Pedido> buscarPedidos(@PathVariable Long idCliente) {

        return pedidos;
    }*/
}
