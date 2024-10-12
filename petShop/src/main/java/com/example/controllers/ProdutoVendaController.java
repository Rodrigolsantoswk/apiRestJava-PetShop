package com.example.controllers;

import com.example.entities.ProdutoVenda;
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
    public ResponseEntity<List<ProdutoVenda>> adicionarProdutoVenda(@RequestBody List<ProdutoVenda> produtosVenda) {
        List<ProdutoVenda> novosProdutosVenda = new ArrayList<>();

        for (ProdutoVenda produtoVenda : produtosVenda) {
            Produto produto = produtoService.buscarPorId(produtoVenda.getProduto().getIdProduto());
            if (produto == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            
            Venda venda = vendaService.buscarPorId(produtoVenda.getVenda().getIdVenda());
            if (venda == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
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