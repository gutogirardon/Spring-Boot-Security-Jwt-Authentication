package com.springcommerceapi.SpringCommerceAPI.resource;

import com.springcommerceapi.SpringCommerceAPI.model.ItemPedido;
import com.springcommerceapi.SpringCommerceAPI.model.ProductNotFoundException;
import com.springcommerceapi.SpringCommerceAPI.service.ItemPedidoService;
import com.springcommerceapi.SpringCommerceAPI.service.PedidoService;
import com.springcommerceapi.SpringCommerceAPI.service.ProdutoService;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.springcommerceapi.SpringCommerceAPI.model.Pedido;
import com.springcommerceapi.SpringCommerceAPI.repository.PedidoRepository;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/pedidos")
public class PedidoResource {

    @Autowired
    ItemPedidoService itemPedidoService;

    @Autowired
    PedidoService pedidoService;

    @Autowired
    ProdutoService produtoService;

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping(value =  "/cadastrar")
    public Pedido salvarPedido(@RequestBody Pedido pedido) {
        for (ItemPedido i : pedido.getItensPedido()) {
            i.setPedido(pedido);
        }
        for (ItemPedido i : pedido.getItensPedido()){
            if (produtoService.buscarProdutoId(i.getProduto().getId()) == null){

                throw new ProductNotFoundException("Produto com ID: " + i.getProduto().getId().toString() + " não foi encontrado");
            }
        }

        return pedidoService.salvarPedido(pedido);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/relatorio/{dataInicio}/{dataFinal}")
    @ResponseBody
    public List<Pedido> relatorioPedidos(@PathVariable String dataInicio, @PathVariable String dataFinal) throws ParseException {
        return pedidoService.relatorio(dataInicio, dataFinal);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @RequestMapping(value = "/cancelarPedido/{idPedido}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Pedido cancelarPedido(@PathVariable Long idPedido) {
    	return pedidoService.alterarPedido(idPedido);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/relatorio/{idCliente}")
    @ResponseBody
    public List<Pedido> relatorioPedidos(@PathVariable Long idCliente) throws ParseException {
        return pedidoService.relatorioPedidoCliente(idCliente);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping(value = "/buscar/{idPedido}", produces = MediaType.APPLICATION_JSON_VALUE)
    
    public @ResponseBody ResponseEntity<Optional<Pedido>> buscarPedido(@PathVariable Long idPedido) {
       
    	
    		if( pedidoService.buscarPedido(idPedido) != null) {
    			return ResponseEntity.status(HttpStatus.OK).body(pedidoService.buscarPedido(idPedido));
    	       }
    		else {
    			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(null);
    		}
    	
    }
}
