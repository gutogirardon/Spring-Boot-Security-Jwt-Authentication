package com.springcommerceapi.SpringCommerceAPI.model;

import java.util.Date;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Entity
@Table(name = "estoque")
public class Estoque {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private LocalDateTime data;
	
	private int tipo;
	
	private int quantidade;

	@OneToOne
	@JoinColumn(name = "produto_id")
	private Produto produtoId;

	public Estoque(int tipo, int quantidade, Produto produtoId) {
		this.tipo = tipo;
		this.quantidade = quantidade;
		this.produtoId = produtoId;
		this.data = LocalDateTime.now(ZoneId.of("-2").normalized());
	}

	public Estoque() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public Produto getProdutoId() {
		return produtoId;
	}

	public void setProdutoId(Produto produtoId) {
		this.produtoId = produtoId;
	}

	@Override
	public String toString() {
		return "Estoque{" +
				"id=" + id +
				", data=" + data +
				", tipo=" + tipo +
				", produtoId=" + produtoId +
				'}';
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
}