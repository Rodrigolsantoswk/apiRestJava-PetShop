package com.example.service;

import com.example.entities.Animal;
import com.example.entities.Cliente;
import java.util.Optional;
import com.example.repository.AnimalRepository;
import com.example.repository.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    public List<Animal> listarTodos() {
        return animalRepository.findAll();
    }

    public Animal buscarPorId(int id) {
        return animalRepository.findById(id).orElse(null);
    }

    public Animal cadastrarAnimal(Animal animal) {
        if (animal.getNome() == null || animal.getNome().isEmpty()) {
            throw new IllegalArgumentException("O nome não pode ser nulo ou vazio");
        }
        if (animal.getIdade() < 0) {
            throw new IllegalArgumentException("A idade não pode ser negativa");
        }
        if (animal.getTipo() == null || animal.getTipo().isEmpty()) {
            throw new IllegalArgumentException("O tipo não pode ser nulo ou vazio");
        }
        if (animal.getRaca() == null || animal.getRaca().isEmpty()) {
            throw new IllegalArgumentException("A raça não pode ser nula ou vazia");
        }
        if (animal.getPeso() <= 0) {
            throw new IllegalArgumentException("O peso não pode ser negativo");
        }
        if (animal.getCliente() == null || animal.getCliente().getIdCliente() == null) {
            throw new IllegalArgumentException("O cliente é obrigatório e o ID do cliente não pode ser nulo.");
        }

        Integer idCliente = animal.getCliente().getIdCliente();
        Optional<Cliente> clienteOpt = clienteRepository.findById(idCliente);

        if (clienteOpt.isPresent()) {
            animal.setCliente(clienteOpt.get());
        } else {
            throw new IllegalArgumentException("Cliente com ID " + idCliente + " não encontrado.");
        }
        return animalRepository.save(animal);
    }

    public void deletarAnimal(int id) {
        animalRepository.deleteById(id);
    }
    
    public Animal atualizarAnimal(int id, Animal animalAtualizado) {
        Optional<Animal> optionalAnimal = animalRepository.findById(id);
        if (optionalAnimal.isPresent()) {
            Animal animalExistente = optionalAnimal.get();

            if (animalAtualizado.getNome() != null && animalAtualizado.getNome().isEmpty()) {
                throw new IllegalArgumentException("O nome não pode ser nulo ou vazio");
            }
            if (animalAtualizado.getIdade() != null && animalAtualizado.getIdade() < 0) {
                throw new IllegalArgumentException("A idade não pode ser negativa");
            }
            if (animalAtualizado.getTipo() != null && animalAtualizado.getTipo().isEmpty()) {
                throw new IllegalArgumentException("O tipo não pode ser nulo ou vazio");
            }
            if (animalAtualizado.getRaca() != null && animalAtualizado.getRaca().isEmpty()) {
                throw new IllegalArgumentException("A raça não pode ser nula ou vazia");
            }
            if (animalAtualizado.getPeso() != null && animalAtualizado.getPeso() < 0) {
                throw new IllegalArgumentException("O peso não pode ser negativo");
            }

            if (animalAtualizado.getNome() != null) {
                animalExistente.setNome(animalAtualizado.getNome());
            }
            if (animalAtualizado.getIdade() != null) {
                animalExistente.setIdade(animalAtualizado.getIdade());
            }
            if (animalAtualizado.getTipo() != null) {
                animalExistente.setTipo(animalAtualizado.getTipo());
            }
            if (animalAtualizado.getRaca() != null) {
                animalExistente.setRaca(animalAtualizado.getRaca());
            }
            if (animalAtualizado.getPeso() != null) {
                animalExistente.setPeso(animalAtualizado.getPeso());
            }

            return animalRepository.save(animalExistente);
        } else {
            throw new IllegalArgumentException("Animal não encontrado com o ID: " + id);
        }
    }
}
