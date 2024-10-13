package com.example.controllers;

import com.example.entities.ProdutoVenda;
import com.example.entities.Item;
import com.example.entities.Produto;
import com.example.entities.Venda;
import com.example.service.ProdutoService;
import com.example.service.ProdutoVendaService;
import com.example.service.VendaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ProdutoVenda")
public class ProdutoVendaController {

    @Autowired
    private ProdutoVendaService produtoVendaService;
    
    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private VendaService vendaService;

    @PostMapping
    public ResponseEntity<?> adicionarProdutoVenda(@RequestBody List<ProdutoVenda> produtosVenda) {
        List<ProdutoVenda> novosProdutosVenda = new ArrayList<>();
        
        for (ProdutoVenda produtoVenda : produtosVenda) {
            if (produtoVenda.getQuantidade() == null || produtoVenda.getQuantidade() < 1) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Map.of("error", "A quantidade deve ser enviada e maior ou igual a 1"));
            }

            Produto produto = produtoService.buscarPorId(produtoVenda.getProduto().getIdProduto());
            if (produto == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Map.of("error", "Produto não encontrado"));
            }
            
            Venda venda = vendaService.consultarVenda(produtoVenda.getVenda().getIdVenda());
            if (venda == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Map.of("error", "Venda não encontrada"));
            }
            
            if (venda.isFinalizada()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Map.of("error", "Não é possível adicionar produtos a uma venda já finalizada"));
            } 
            
            if (produto instanceof Item) {
                Item item = (Item) produto;
                if (item.getEstoque() < produtoVenda.getQuantidade()) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body(Map.of("error", "Estoque insuficiente para o produto: " + item.getNome()));
                }
                item.setEstoque(item.getEstoque() - produtoVenda.getQuantidade());
                produtoService.atualizarProduto(item.getIdProduto(), item);
            }

            produtoVenda.setVenda(venda);
            produtoVenda.setValor(produto.getPreco());
            ProdutoVenda novoProdutoVenda = produtoVendaService.adicionarProdutoVenda(produtoVenda);
            novosProdutosVenda.add(novoProdutoVenda);
        }

        if (!produtosVenda.isEmpty()) {
            vendaService.atualizarValorTotal(produtosVenda.get(0).getVenda());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(novosProdutosVenda);
    }
}