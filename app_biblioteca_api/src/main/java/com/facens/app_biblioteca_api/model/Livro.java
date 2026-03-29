package com.facens.app_biblioteca_api.model;

import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "livros")
@Data 
@NoArgsConstructor
@AllArgsConstructor 
@Builder

public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String autor;

    @Builder.Default
    private Boolean emprestado = false;
    private LocalDate dataEmprestimo;
    
}