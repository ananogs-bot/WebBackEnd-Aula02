package com.facens.app_biblioteca_api.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.facens.app_biblioteca_api.model.Livro;
import com.facens.app_biblioteca_api.repository.LivroRepository;

@Service
public class LivroService {
    @Autowired
    private LivroRepository livroRepository;

    // CRUD - Create, Read, Update, Delete
    public List<Livro> getLivros() {
        return livroRepository.findAll();
    }

    public Livro getLivro(Long id) {
        if (id == null) {
            return null;
        } else {
            return livroRepository.findById(id).orElse(null);
        }
    }

    public Livro postLivro(Livro livro) {
        livro.setEmprestado(false);
        return livroRepository.save(livro);
    }

    public Livro putLivro(Long id, Livro livro) {
        Livro livroExistente = getLivro(id);
        if (livroExistente != null) {
            livroExistente.setTitulo(livro.getTitulo());
            livroExistente.setAutor(livro.getAutor());
            return livroRepository.save(livroExistente);
        }
        return null;
    }

    public void deleteLivro(Long id) {
        livroRepository.deleteById(id);
    }

    // Empréstimo de livro e Devolução de livro
    public Livro emprestarLivro(Long id) {
        Livro livro = getLivro(id);
        if (livro != null && !livro.isEmprestado()) {
            livro.setEmprestado(true);
            livro.setDataEmprestimo(LocalDate.now().toString());
            return livroRepository.save(livro);
        }
        return null;
    }

    public Livro devolverLivro(Long id) {
        Livro livro = getLivro(id);
        if (livro != null && livro.isEmprestado()) {
            livro.setEmprestado(false);
            livro.setDataEmprestimo(null);
            return livroRepository.save(livro);
        }
        return null;
    }
};