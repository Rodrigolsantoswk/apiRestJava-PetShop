package com.example.service;

import com.example.entities.Item;
import com.example.entities.Produto;
import com.example.entities.Servico;
import com.example.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    public Produto buscarPorId(int id) {
        return produtoRepository.findById(id).orElse(null);
    }

    public Produto salvar(Produto produto) {
        if (produto.getNome() == null || produto.getNome().isEmpty()) {
            throw new IllegalArgumentException("O nome não pode ser nulo ou vazio");
        }
        if (produto.getPreco() == null || produto.getPreco() <= 0) {
            throw new IllegalArgumentException("O preço deve ser maior que zero");
        }

        if (produto instanceof Item) {
            Item item = (Item) produto;

            if (item.getEstoque() <= 0) {
                throw new IllegalArgumentException("O estoque não pode ser negativo ou igual a 0");
            }
        }

        if (produto instanceof Servico) {
            Servico servico = (Servico) produto;
            if (servico.getDuracao() <= 0) {
                throw new IllegalArgumentException("A duração do serviço deve ser maior que zero");
            }
        }

        return produtoRepository.save(produto);
    }
 
    public void deletar(int id) {
        produtoRepository.deleteById(id);
    }
    
    public Produto atualizarProduto(Integer id, Produto produtoAtualizado) {
        Optional<Produto> produtoExistente = produtoRepository.findById(id);
        if (!produtoExistente.isPresent()) {
            throw new IllegalArgumentException("Produto não encontrado com o ID: " + id);
        }

        Produto produto = produtoExistente.get();
        if (produtoAtualizado.getNome() != null) {
            if (produtoAtualizado.getNome().isEmpty()) {
                throw new IllegalArgumentException("O nome não pode ser vazio");
            }
            produto.setNome(produtoAtualizado.getNome());
        }

        if (produtoAtualizado.getPreco() != null) {
            if (produtoAtualizado.getPreco() <= 0) {
                throw new IllegalArgumentException("O preço deve ser maior que zero");
            }
            produto.setPreco(produtoAtualizado.getPreco());
        }

        if (produtoAtualizado instanceof Item) {
            Item itemAtualizado = (Item) produtoAtualizado;
            if (itemAtualizado.getEstoque() != null) {
                if (itemAtualizado.getEstoque() < 0) {
                    throw new IllegalArgumentException("O estoque não pode ser negativo");
                }
                ((Item) produto).setEstoque(itemAtualizado.getEstoque());
            }
            if (itemAtualizado.getPosicao() != null) {
                ((Item) produto).setPosicao(itemAtualizado.getPosicao());
            }
        }

        if (produtoAtualizado.getDescricao() != null) {
            produto.setDescricao(produtoAtualizado.getDescricao());
        }

        return produtoRepository.save(produto);
    }
    
}
