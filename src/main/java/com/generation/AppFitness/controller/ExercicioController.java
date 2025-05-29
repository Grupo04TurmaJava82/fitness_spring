package com.generation.AppFitness.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.AppFitness.model.Exercicio;
import com.generation.AppFitness.repository.ExercicioRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/exercicio")
@CrossOrigin(origins ="*", allowedHeaders = "*")
public class ExercicioController {

    @Autowired
    private ExercicioRepository exercicioRepository;

    @GetMapping
    public ResponseEntity<List<Exercicio>> getAll(){
        return ResponseEntity.ok(exercicioRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Exercicio> getById(@PathVariable Long id){
        return exercicioRepository.findById(id)
            .map(resposta -> ResponseEntity.ok(resposta))
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Exercicio>> getByNome(@PathVariable String nome){
        return ResponseEntity.ok(exercicioRepository.findAllByNomeContainingIgnoreCase(nome));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Exercicio> postExercicio(@Valid @RequestBody Exercicio exercicio){
        return ResponseEntity.status(HttpStatus.CREATED).body(exercicioRepository.save(exercicio));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<Exercicio> putExercicio(@Valid @RequestBody Exercicio exercicio) {
        if (exercicio.getId() == null) {
            return ResponseEntity.badRequest().build();
        }
        
        return exercicioRepository.findById(exercicio.getId())
                .map(respostaExistente -> {
                    return ResponseEntity.ok().body(exercicioRepository.save(exercicio));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteExercicio(@PathVariable Long id) {
        return exercicioRepository.findById(id)
                .map(resposta -> {
                    exercicioRepository.deleteById(id);
                    return ResponseEntity.status(HttpStatus.NO_CONTENT).<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}