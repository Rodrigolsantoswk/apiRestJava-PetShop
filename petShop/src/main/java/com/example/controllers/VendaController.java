package com.example.controllers;

import com.example.entities.Venda;
import com.example.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendas")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @GetMapping
    public List<Venda> listarTodas() {
        return vendaService.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venda> buscarPorId(@PathVariable int id) {
        Venda venda = vendaService.consultarVenda(id);
        if (venda != null) {
            return ResponseEntity.ok(venda);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping
    public ResponseEntity<Venda> criarVenda(@RequestBody Venda venda) {
        Venda novaVenda = vendaService.criarVenda(venda);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaVenda);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        vendaService.deletarVenda(id);
        return ResponseEntity.noContent().build();
    }
    
    @PutMapping("/finalizar/{id}")
    public ResponseEntity<Void> finalizarVenda(@PathVariable Integer id) {
        vendaService.finalizarVenda(id);
        return ResponseEntity.ok().build();
    }
}
