package com.example.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.entities.Cliente;
import com.example.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    public Cliente buscarPorId(Integer id) {
        return clienteRepository.findById(id).orElse(null);
    }

    public Cliente salvar(Cliente cliente) {
    	if (cliente.getNome() == null || cliente.getNome().isEmpty()) {
            throw new IllegalArgumentException("O nome não pode ser nulo ou vazio");
        }
        if (cliente.getTelefone() == null || cliente.getTelefone().isEmpty()) {
            throw new IllegalArgumentException("O telefone não pode ser nulo ou vazio");
        }
        if (!cliente.getTelefone().matches("\\d{10}|\\d{11}")) {
            throw new IllegalArgumentException("O telefone deve ter 10 ou 11 dígitos, contendo apenas números");
        }
        if (cliente.getEmail() == null || cliente.getEmail().isEmpty()) {
            throw new IllegalArgumentException("O email não pode ser nulo ou vazio");
        }
        if (cliente.getEndereco() == null || cliente.getEndereco().isEmpty()) {
            throw new IllegalArgumentException("O endereço não pode ser nulo ou vazio");
        }
        return clienteRepository.save(cliente);
    }

    public void deletar(Integer id) {
        clienteRepository.deleteById(id);
    }

    public Cliente atualizarCliente(int id, Cliente clienteAtualizado) {
        Optional<Cliente> optionalCliente = clienteRepository.findById(id);
        if (optionalCliente.isPresent()) {
        	
        	if (clienteAtualizado.getNome() != null && clienteAtualizado.getNome().isEmpty()) {
                throw new IllegalArgumentException("O nome não pode ser nulo ou vazio");
            }
            if (clienteAtualizado.getTelefone() != null && clienteAtualizado.getTelefone().isEmpty()) {
                throw new IllegalArgumentException("O telefone não pode ser nulo ou vazio");
            }
            if (clienteAtualizado.getTelefone() != null && !clienteAtualizado.getTelefone().matches("\\d{10}|\\d{11}")) {
                throw new IllegalArgumentException("O telefone deve ter 10 ou 11 dígitos, contendo apenas números");
            }
            if (clienteAtualizado.getEmail() != null && clienteAtualizado.getEmail().isEmpty()) {
                throw new IllegalArgumentException("O email não pode ser nulo ou vazio");
            }
            if (clienteAtualizado.getEndereco() != null && clienteAtualizado.getEndereco().isEmpty()) {
                throw new IllegalArgumentException("O endereço não pode ser nulo ou vazio");
            }
            Cliente clienteExistente = optionalCliente.get();
            if (clienteAtualizado.getEmail() != null) {
                clienteExistente.setEmail(clienteAtualizado.getEmail());
            }
            if (clienteAtualizado.getEndereco() != null) {
                clienteExistente.setEndereco(clienteAtualizado.getEndereco());
            }
            if (clienteAtualizado.getTelefone() != null) {
                clienteExistente.setTelefone(clienteAtualizado.getTelefone());
            }
            if (clienteAtualizado.getNome() != null) {
                clienteExistente.setNome(clienteAtualizado.getNome());
            }
            return clienteRepository.save(clienteExistente);
        } else {
            throw new IllegalArgumentException("Cliente não encontrado com o ID: " + id);
        }
    }
}
