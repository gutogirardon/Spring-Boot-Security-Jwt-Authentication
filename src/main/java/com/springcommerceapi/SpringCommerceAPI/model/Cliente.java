package com.springcommerceapi.SpringCommerceAPI.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.web.bind.annotation.ResponseBody;

@Entity
@Table (name = "cliente")
public class Cliente {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;	
	private String nome;
	private String sobrenome;
	private Date dataNascimento;
	private Long telefone;
	private Long cpf;
	private String sexo;
	private String dataRegistro;
	private String endereco;
	private String numero;
	private String bairro;
	private int cep;
	private String complemento;
	
	public Cliente(Long id, String nome, String sobrenome, Date dataNascimento, Long telefone, Long cpf, String sexo,
			String dataRegistro, String endereco, String numero, String bairro, int cep, String complemento) {
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.dataNascimento = dataNascimento;
		this.telefone = telefone;
		this.cpf = cpf;
		this.sexo = sexo;
		this.dataRegistro = dataRegistro;
		this.endereco = endereco;
		this.numero = numero;
		this.bairro = bairro;
		this.cep = cep;
		this.complemento = complemento;
	}

	public Cliente() {
		// TODO Auto-generated constructor stub
	}
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
    private List<Pedido> pedidos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public long getTelefone() {
		return telefone;
	}

	public void setTelefone(Long telefone) {
		this.telefone = telefone;
	}

	public long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getDataRegistro() {
		return dataRegistro;
	}

	public void setDataRegistro(String dataRegistro) {
		this.dataRegistro = dataRegistro;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public int getCep() {
		return cep;
	}

	public void setCep(int cep) {
		this.cep = cep;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	
	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	@Override
	public String toString() {
		return "Cliente id=" + id + ", nome=" + nome + ", sobrenome=" + sobrenome + ", dataNascimento="
				+ dataNascimento + ", telefone=" + telefone + ", cpf=" + cpf + ", sexo=" + sexo + ", dataRegistro="
				+ dataRegistro + ", endereco=" + endereco + ", numero=" + numero + ", bairro=" + bairro + ", cep=" + cep
				+ ", complemento=" + complemento + ", pedidos=" + pedidos + "\n";
	}
	
}