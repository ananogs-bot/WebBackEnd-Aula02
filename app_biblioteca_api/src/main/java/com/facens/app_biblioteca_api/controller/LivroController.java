package com.facens.app_biblioteca_api.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.facens.app_biblioteca_api.model.Livro;
import com.facens.app_biblioteca_api.service.LivroService;

@RestController 
//Combinação do Controller com o ResponseBody, ou seja, já retorna o JSON
@RequestMapping("/livros") 
//Define o caminho base para as requisições relacionadas aos livros

public class LivroController {
    @Autowired
    private LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @GetMapping
    public List<Livro> listarLivros() {
        return livroService.listarTodos();
    }

    @GetMapping("/teste")
    public String testeAPI() {
        return "API de Biblioteca funcionando!";
    }

    @GetMapping("/{id}") //Define que o método responderá a requisições GET com um ID específico
    public Livro buscaPorId(@PathVariable Long id) {
        return livroService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) //Define o status HTTP 201 para indicar que um recurso foi criado com sucesso
    public Livro criarLivro(@RequestBody Livro livro) {
        return livroService.criar(livro);
    }

    @PutMapping("/{id}")
    public Livro atualizarLivro(@PathVariable Long id, @RequestBody Livro livro) {
        return livroService.atualizar(id, livro);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) //Define o status HTTP 204 para indicar que a requisição foi bem-sucedida, mas não há conteúdo para retornar
    public Livro removerLivro(@PathVariable Long id) {
        return livroService.deletar(id);
    }

    @PutMapping("/{id}/emprestar")
    public Livro emprestarLivro(@PathVariable Long id) {
        return livroService.emprestar(id);
    }

    @PutMapping("/{id}/devolver")
    public Livro devolverLivro(@PathVariable Long id) {
        return livroService.devolver(id);
    }
}
