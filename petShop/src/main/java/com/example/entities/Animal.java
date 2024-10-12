package com.example.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Animal")
public class Animal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer IDAnimal;
	private String Nome;
	private Integer Idade;
	private String Tipo;
	private String Raca;
	private Float Peso;
	@ManyToOne
    @JoinColumn(name = "idCliente", referencedColumnName = "idCliente", nullable = false)
	private Cliente cliente;
	
	public Animal(String nome, int idade, String tipo, String raca, Float peso, Cliente cliente) {
		Nome = nome;
		Idade = idade;
		Tipo = tipo;
		Raca = raca;
		Peso = peso;
		this.cliente = cliente;
	}
	
	public Animal() {
	}
	
	public Integer getIDAnimal() {
		return IDAnimal;
	}
	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome;
	}
	public Integer getIdade() {
		return Idade;
	}
	public void setIdade(int idade) {   
		Idade = idade;
	}
	public String getTipo() {
		return Tipo;
	}
	public void setTipo(String tipo) {
		Tipo = tipo;
	}
	public String getRaca() {
		return Raca;
	}
	public void setRaca(String raca) {
		Raca = raca;
	}
	public Float getPeso() {
		return Peso;
	}
	public void setPeso(Float peso) {
		Peso = peso;
	}
	public Cliente getCliente() {
		return this.cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
}
