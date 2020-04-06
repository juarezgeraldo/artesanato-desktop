package com.br.juarezjunior.artesanato.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "tb_fornecedor")
public class Fornecedor implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	private String nome;
	private Double numeroTel1;
	private Double numeroTel2;
	private String endereco;
	private String observacao;

	@JsonIgnore
	@ManyToMany(mappedBy = "fornecedores")
	private Set<Produto> produtos = new HashSet<>();

	public Fornecedor() {
	}

	public Fornecedor(Long codigo, String nome, Double numeroTel1, Double numeroTel2, String endereco,
			String observacao) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.numeroTel1 = numeroTel1;
		this.numeroTel2 = numeroTel2;
		this.endereco = endereco;
		this.observacao = observacao;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getNumeroTel1() {
		return numeroTel1;
	}

	public void setNumeroTel1(Double numeroTel1) {
		this.numeroTel1 = numeroTel1;
	}

	public Double getNumeroTel2() {
		return numeroTel2;
	}

	public void setNumeroTel2(Double numeroTel2) {
		this.numeroTel2 = numeroTel2;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Set<Produto> getProdutos() {
		return produtos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fornecedor other = (Fornecedor) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
}
