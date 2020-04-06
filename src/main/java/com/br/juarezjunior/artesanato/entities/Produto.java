package com.br.juarezjunior.artesanato.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_produto")
public class Produto implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	private String nome;
	private Integer quantidadeEstoque;
	private Double custoMedio;
	private Double precoVenda;
	private Integer estoqueMinimo;
	
	@ManyToMany
	@JoinTable(name = "tb_produto_categoria", 
				joinColumns = @JoinColumn(name = "produto_id"),
				inverseJoinColumns = @JoinColumn(name = "categoria_id"))
	private Set<Categoria> categorias = new HashSet<>();
	
	@ManyToMany
	@JoinTable(name = "tb_produto_fornecedor", 
				joinColumns = @JoinColumn(name = "produto_id"),
				inverseJoinColumns = @JoinColumn(name = "fornecedor_id"))
	private Set<Fornecedor> fornecedores = new HashSet<>();

	
	@OneToMany(mappedBy = "id.produto")
	private Set<ItemPedido> itens = new HashSet<>();
	public Produto() {
	}

	public Produto(Long codigo, String nome, Integer quantidadeEstoque, Double custoMedio, Double precoVenda,
			Integer estoqueMinimo) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.quantidadeEstoque = quantidadeEstoque;
		this.custoMedio = custoMedio;
		this.precoVenda = precoVenda;
		this.estoqueMinimo = estoqueMinimo;
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

	public Integer getQuantidadeEstoque() {
		return quantidadeEstoque;
	}

//	public void setQuantidadeEstoque(Integer quantidadeEstoque) {
//		this.quantidadeEstoque = quantidadeEstoque;
//	}
//
	public Double getCustoMedio() {
		return custoMedio;
	}

	public void setCustoMedio(Double custoMedio) {
		this.custoMedio = custoMedio;
	}

	public Double getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(Double precoVenda) {
		this.precoVenda = precoVenda;
	}

	public Integer getEstoqueMinimo() {
		return estoqueMinimo;
	}

	public void setEstoqueMinimo(Integer estoqueMinimo) {
		this.estoqueMinimo = estoqueMinimo;
	}

	public Set<Categoria> getCategorias() {
		return categorias;
	}

	public Set<Fornecedor> getFornecedores() {
		return fornecedores;
	}
	
	@JsonIgnore
	public Set<Pedido> getPedidos(){
		Set<Pedido> set = new HashSet<>();
		for (ItemPedido x : itens) {
			set.add(x.getPedido());
		}
		return set;
	}
	
	public void atualizaEstoque(Integer quantidade) {
		this.quantidadeEstoque += quantidade;
	}

	public Double getValorTotalEstoqueVenda() {
		return this.quantidadeEstoque * this.precoVenda;
	}
	
	public Double getValorTotalEstoqueCusto() {
		return this.quantidadeEstoque * this.custoMedio;
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
		Produto other = (Produto) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	
}
