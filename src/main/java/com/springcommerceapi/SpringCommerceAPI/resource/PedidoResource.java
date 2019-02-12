package com.springcommerceapi.SpringCommerceAPI.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springcommerceapi.SpringCommerceAPI.model.Cliente;
import com.springcommerceapi.SpringCommerceAPI.model.Pedido;
import com.springcommerceapi.SpringCommerceAPI.model.Produto;
import com.springcommerceapi.SpringCommerceAPI.repository.ClienteRepository;
import com.springcommerceapi.SpringCommerceAPI.repository.PedidoRepository;
import com.springcommerceapi.SpringCommerceAPI.repository.ProdutoRepository;
import com.springcommerceapi.SpringCommerceAPI.service.ProdutoService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/pedidos")
public class PedidoResource {
	
	@Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PedidoRepository pedidoRepository;
    
    @Autowired
	ProdutoService produtoService;
    
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
	@PostMapping(value =  "/comprar/{idCliente}/{idProduto}/{quantidade}")
    public String comprar(@PathVariable Long idCliente, @PathVariable Long idProduto, @PathVariable Integer quantidade) {
        Cliente cliente = clienteRepository.findById(idCliente).orElse(null);
        Produto produto = produtoRepository.findById(idProduto).orElse(null);
    	
        Pedido pedido = new Pedido(cliente);
        pedido.comprar(produto, quantidade);

        pedidoRepository.save(pedido);
        produtoService.salvarProduto(produto, 2, quantidade);

        return "Sucesso na compra do produto: " + produto.getNome() + "\n";
    }
    
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @RequestMapping(value = "/compras/{idCliente}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String comprar(@PathVariable Long idCliente) {
        System.out.println("Nome do cliente: "+idCliente);
        Cliente cliente = clienteRepository.findById(idCliente).orElse(null);
        //return 	"Nome completo: " + cliente.getNome() + " " + cliente.getSobrenome() + " CPF: " +
        		//cliente.getCpf() + "\n" +
        		//cliente.getPedidos() + "\n";        		      
        return cliente.getPedidos() + "";
    }
    
    

	
	
	
}
