package com.example.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Cliente")
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCliente;
	private String Nome;
	private String Telefone;
	private String Email;
	private String Endereco;
	
	public Cliente(String nome, String telefone, String email, String endereco) {
		Nome = nome;
		Telefone = telefone;
		Email = email;
		Endereco = endereco;
	}
	
	public Cliente() {
    }
	
	public Integer getIdCliente() {
		return idCliente;
	}
	
	public void setIDCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}
	
	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome;
	}
	public String getTelefone() {
		return Telefone;
	}
	public void setTelefone(String telefone) {
		Telefone = telefone;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getEndereco() {
		return Endereco;
	}
	public void setEndereco(String endereco) {
		Endereco = endereco;
	}
}
