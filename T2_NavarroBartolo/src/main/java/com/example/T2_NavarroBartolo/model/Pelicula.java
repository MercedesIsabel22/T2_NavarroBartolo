package com.example.T2_NavarroBartolo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@Entity
@Table(name = "pelicula")
public class Pelicula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 45)
    private String nombre;
    @Size(max = 45)
    private String director;
    private Date fechaEstreno;
    @ManyToOne
    @JoinColumn(name = "genero_id")
    private Genero genero;
}
