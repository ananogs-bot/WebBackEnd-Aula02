package com.facens.app_biblioteca_api.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.facens.app_biblioteca_api.model.Livro;
import com.facens.app_biblioteca_api.service.LivroService;

@RestController
@RequestMapping("/livros")
public class LivroController {
    @Autowired
    private LivroService livroService;

    @GetMapping
    public List<Livro> getLivros() {
        return livroService.getLivros();
    }

    @GetMapping("/teste")
    public String teste() {
        return "API de Biblioteca funcionando!";
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> getLivro(@PathVariable Long id) {
        Livro livro = livroService.getLivro(id);
        if (livro != null) {
            return ResponseEntity.ok(livro);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Livro postLivro(@RequestBody Livro livro) {
        return livroService.postLivro(livro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro> putLivro(@PathVariable Long id, @RequestBody Livro livro) {
        Livro livroAtualizado = livroService.putLivro(id, livro);
        if (livroAtualizado != null) {
            return ResponseEntity.ok(livroAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLivro(@PathVariable Long id) {
        livroService.deleteLivro(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/emprestar")
    public ResponseEntity<Livro> emprestarLivro(@PathVariable Long id) {
        Livro livroEmprestado = livroService.emprestarLivro(id);
        if (livroEmprestado != null) {
            return ResponseEntity.ok(livroEmprestado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/devolver")
    public ResponseEntity<Livro> devolverLivro(@PathVariable Long id) {
        Livro livroDevolvido = livroService.devolverLivro(id);
        if (livroDevolvido != null) {
            return ResponseEntity.ok(livroDevolvido);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
