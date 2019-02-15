package com.springcommerceapi.SpringCommerceAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "item_pedido")
public class ItemPedido {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double preco_unitario;
    private Integer quantidade;
    private Double preco_total;
	
    @ManyToOne(optional = false)
    @JoinColumn(name = "pedido_id", referencedColumnName = "id")
	@JsonIgnore
    private Pedido pedido;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "produto_id", referencedColumnName = "id")
    private Produto produto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}	

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPreco_total() {
		return preco_total;
	}

	public void setPreco_total(Double preco_total) {
		this.preco_total = preco_total;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Double getPreco_unitario() {
		return preco_unitario;
	}

	public void setPreco_unitario(Double preco_unitario) {
		this.preco_unitario = preco_unitario;
	}

	@Override
	public String toString() {
		return "Produto id: " + id + ", Nome: " + produto.getNome() + ", Descrição: " + produto.getDescricao() + ", Preco Unitario: " + preco_unitario + ", Qtde: " + quantidade
				+ ", Valor Total: " + preco_total + "\n";
	}

	
}
