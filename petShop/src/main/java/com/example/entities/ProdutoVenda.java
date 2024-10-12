package com.example.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ProdutoVenda")
public class ProdutoVenda {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idItemVenda;
    @ManyToOne
    @JoinColumn(name = "idProduto", nullable = false)
    private Produto produto;
    
    @ManyToOne
    @JoinColumn(name = "idVenda", nullable = false)
    private Venda venda;
    private int quantidade; 
    private Double valor;
    
	public ProdutoVenda(int idItemVenda, Produto produto, Venda venda, int quantidade, Double valor) {
		this.idItemVenda = idItemVenda;
		this.produto = produto;
		this.venda = venda;
		this.quantidade = quantidade;
		this.valor = valor;
	}
	
	public ProdutoVenda () {
		
	}
	
	public int getIdItemVenda() {
		return idItemVenda;
	}
	public void setIdItemVenda(int idItemVenda) {
		this.idItemVenda = idItemVenda;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public Venda getVenda() {
		return venda;
	}
	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
}
