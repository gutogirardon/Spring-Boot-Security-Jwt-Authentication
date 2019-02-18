package com.springcommerceapi.SpringCommerceAPI.resource;

import com.springcommerceapi.SpringCommerceAPI.model.ItemPedido;
import com.springcommerceapi.SpringCommerceAPI.service.ItemPedidoService;
import com.springcommerceapi.SpringCommerceAPI.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.springcommerceapi.SpringCommerceAPI.model.Pedido;

import java.text.ParseException;
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
    @PostMapping(value =  "/cadastrar")
    public Pedido salvarPedido(@RequestBody Pedido pedido) {
        for (ItemPedido i : pedido.getItensPedido()) {
            i.setPedido(pedido);
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
    public String relatorioPedidos(@PathVariable Long idCliente) throws ParseException {
        return pedidoService.relatorioPedidoCliente(idCliente) + "";
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping(value = "/buscar/{idPedido}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Pedido buscarPedido(@PathVariable Long idPedido) {
        return pedidoService.buscarPedido(idPedido);
    }
}
