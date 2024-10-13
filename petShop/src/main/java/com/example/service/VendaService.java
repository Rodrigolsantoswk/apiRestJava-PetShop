package com.example.service;

import com.example.entities.Cliente;
import com.example.entities.Venda;
import com.example.repository.ClienteRepository;
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
    private ClienteRepository clienteRepository;
    
    @Autowired
    private ProdutoVendaService produtoVendaService;

    public List<Venda> listarTodas() {
        return vendaRepository.findAll();
    }
    
    @Transactional
    public Venda consultarVenda(int id) {
        return vendaRepository.findById(id).orElse(null);
    }

    public Venda criarVenda(Venda venda) {
        if (venda.getCliente() == null || venda.getCliente().getIdCliente() == null) {
            throw new IllegalArgumentException("O cliente deve estar preenchido e ser válido");
        }
        Cliente cliente = clienteRepository.findById(venda.getCliente().getIdCliente())
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));
        venda.setCliente(cliente);
        venda.setValorTotal(0.0);
        return vendaRepository.save(venda);
    }
    
    public void atualizarValorTotal(Venda venda) {
    	if (venda.isFinalizada()) {
	        throw new IllegalArgumentException("Não é possível atualizar uma venda já finalizada");
	    }
        double valorTotal = produtoVendaService.calcularValorTotal(venda);
        venda.setValorTotal(valorTotal);
        vendaRepository.save(venda);
    }

    public void deletarVenda(int id) {
    	Venda venda = vendaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Venda não encontrada"));
    	if (venda.isFinalizada()) {
            throw new IllegalArgumentException("Não é possível excluir uma venda já finalizada");
        }
        vendaRepository.deleteById(id);
    }
    
    public void finalizarVenda(int id) {
        Venda venda = vendaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Venda não encontrada"));
        venda.setFinalizada(true);
        vendaRepository.save(venda);
    }
}