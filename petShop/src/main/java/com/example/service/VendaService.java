package com.example.service;

import com.example.entities.Venda;
import com.example.repository.VendaRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VendaService {


    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private ProdutoVendaService produtoVendaService;

    public List<Venda> listarTodas() {
        return vendaRepository.findAll();
    }

    @Transactional
    public Venda buscarPorId(int id) {
        return vendaRepository.findById(id).orElse(null);
    }

    public Venda criarVenda(Venda venda) {
        venda.setValorTotal(0.0);
        return vendaRepository.save(venda);
    }

    public void atualizarValorTotal(Venda venda) {
        double valorTotal = produtoVendaService.calcularValorTotal(venda);
        venda.setValorTotal(valorTotal);
        vendaRepository.save(venda);
    }

    public void deletar(int id) {
        vendaRepository.deleteById(id);
    }
}