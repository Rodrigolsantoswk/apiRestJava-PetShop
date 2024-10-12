package com.example.service;

import com.example.entities.ProdutoVenda;
import com.example.entities.Venda;
import com.example.repository.ProdutoVendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
