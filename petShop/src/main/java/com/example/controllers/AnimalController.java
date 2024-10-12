package com.example.controllers;

import com.example.entities.Animal;
import com.example.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/animais")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @GetMapping
    public List<Animal> listarTodos() {
        return animalService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Animal> buscarPorId(@PathVariable int id) {
        Animal animal = animalService.buscarPorId(id);
        if (animal != null) {
            return ResponseEntity.ok(animal);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody Animal animal) {
    	try {
    		Animal novoAnimal = animalService.salvar(animal);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoAnimal);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ErrorController(e.getMessage()));
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarAnimal(@PathVariable int id, @RequestBody Animal animalAtualizado) {
    	try {
            Animal animal = animalService.atualizarAnimal(id, animalAtualizado);
            return ResponseEntity.ok(animal);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ErrorController(e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        animalService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
