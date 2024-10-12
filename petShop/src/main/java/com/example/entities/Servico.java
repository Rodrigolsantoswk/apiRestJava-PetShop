package com.example.entities;

import jakarta.persistence.Entity;

@Entity
public class Servico extends Produto{
	private int duracao;

	public Servico(String nome, String descricao, double preco, int duracao) {
		super(nome, descricao, preco);
		this.duracao = duracao;
	}

	public Servico() {
		
	}
	
	public int getDuracao() {
		return duracao;
	}

	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}
}
