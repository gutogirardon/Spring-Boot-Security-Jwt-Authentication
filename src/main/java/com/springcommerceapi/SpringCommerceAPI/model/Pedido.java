package com.springcommerceapi.SpringCommerceAPI.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "pedido")
public class Pedido {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private Date data_pedido;
	private double valor_total;
	private int status;
	
	@ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private Cliente cliente;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedido")
    private List<ItemPedido> itensPedido;
    
	
    Pedido() {
        // default constructor
    }

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
        this.data_pedido = new Date();
    }

    public void comprar(Produto produto, Integer quantidade) {
        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setPreco_unitario(produto.getValor());
        //tem que calcular se é menor ou igual a quantidade do produto
        //itemPedido.setQuantidade(quantidade);
        if (quantidade <= produto.getQuantidade() && quantidade > 0) {
        	itemPedido.setQuantidade(quantidade);
        	produto.setQuantidade(produto.getQuantidade() - quantidade);
		} else {
			throw new IllegalArgumentException();
		}
        itemPedido.setPreco_total(produto.getValor() * quantidade);
        itemPedido.setProduto(produto);
        itemPedido.setPedido(this);

        this.itensPedido = new ArrayList<>();
        this.itensPedido.add(itemPedido);

        List<Pedido> pedidosDoCliente = new ArrayList<>();
        if(cliente.getPedidos() != null) {
            pedidosDoCliente = cliente.getPedidos();
        }
        pedidosDoCliente.add(this);
        cliente.setPedidos(pedidosDoCliente);

        List<ItemPedido> itensPedidosProduto = new ArrayList<>();
        if(produto.getItensPedido() != null) {
            itensPedidosProduto = produto.getItensPedido();
        }
        itensPedidosProduto.add(itemPedido);
        produto.setItensPedido(itensPedidosProduto);

        this.setValor_total(itemPedido.getPreco_total());
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData_pedido() {
		return data_pedido;
	}

	public void setData_pedido(Date data_pedido) {
		this.data_pedido = data_pedido;
	}

	public double getValor_total() {
		return valor_total;
	}

	public void setValor_total(double valor_total) {
		this.valor_total = valor_total;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<ItemPedido> getItensPedido() {
		return itensPedido;
	}

	public void setItensPedido(List<ItemPedido> itensPedido) {
		this.itensPedido = itensPedido;
	}

	@Override
	public String toString() {
		return "\nPedido: " + id + ", Data: " + data_pedido + ", Valor Total: " + valor_total + ", Status: "
				+ status + " Lista dos itens: " + itensPedido;
	}
	
}
