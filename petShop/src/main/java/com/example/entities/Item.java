package com.example.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
public class Item extends Produto {
    private Integer estoque;
    @Enumerated(EnumType.STRING) 
    private Posicao posicao;

    public Item(String nome, String descricao, Double preco, int estoque, Posicao posicao) {
        super(nome, descricao, preco);  
        this.estoque = estoque;
        this.posicao = posicao;
    }
    
    public Item () {
    	
    }
    
    public Integer getEstoque() {
        return estoque;
    }
    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }
    public Posicao getPosicao() {
        return posicao;
    }
    public void setPosicao(Posicao posicao) {
        this.posicao = posicao;
    }
}
