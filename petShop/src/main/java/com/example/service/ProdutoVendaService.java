package com.example.service;

import com.example.entities.ProdutoVenda;
import com.example.entities.Venda;
import com.example.repository.ProdutoVendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProdutoVendaService {

	@Autowired
    private ProdutoVendaRepository produtoVendaRepository;

    public ProdutoVenda adicionarProdutoVenda(ProdutoVenda produtoVenda) {
        return produtoVendaRepository.save(produtoVenda);
    }
    
    public List<ProdutoVenda> buscarPorVenda(Venda venda) {
        return produtoVendaRepository.findByVenda(venda);
    }

    public double calcularValorTotal(Venda venda) {
        List<ProdutoVenda> produtosVenda = produtoVendaRepository.findByVenda(venda);
        double valorTotal = 0.0;
        for (ProdutoVenda pv : produtosVenda) {
            valorTotal += pv.getQuantidade() * pv.getValor();
        }
        return valorTotal;
    }
    
    public Map<String, Object> buscarProdutosPorVenda(Integer idVenda) {
        List<ProdutoVenda> produtosVenda = produtoVendaRepository.buscarProdutosPorVenda(idVenda);
        
        if (produtosVenda.isEmpty()) {
            throw new IllegalArgumentException("Nenhum produto encontrado para a venda ID: " + idVenda);
        }

        Venda venda = produtosVenda.get(0).getVenda();
        List<Map<String, Object>> produtosList = produtosVenda.stream().map(produtoVenda -> {
            Map<String, Object> produtoMap = new HashMap<>();
            produtoMap.put("idItemVenda", produtoVenda.getIdItemVenda());
            produtoMap.put("produto", produtoVenda.getProduto());
            produtoMap.put("quantidade", produtoVenda.getQuantidade());
            produtoMap.put("valor", produtoVenda.getValor());
            return produtoMap;
        }).collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("venda", venda);
        response.put("produtosVenda", produtosList);

        return response;
    }
}
