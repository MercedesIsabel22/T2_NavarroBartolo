package com.example.T2_NavarroBartolo.service;

import com.example.T2_NavarroBartolo.model.Genero;
import com.example.T2_NavarroBartolo.model.Pelicula;
import com.example.T2_NavarroBartolo.repository.GeneroRepository;
import com.example.T2_NavarroBartolo.repository.PeliculaRepository;
import com.example.T2_NavarroBartolo.request.RequestPelicula;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PeliculaService {
    @Autowired
    private PeliculaRepository peliculaRepository;
    @Autowired
    private GeneroRepository generoRepository;

    public PeliculaService(PeliculaRepository peliculaRepository) {
        this.peliculaRepository = peliculaRepository;
    }
    public List<Pelicula> getAllPeliculas() {
        return peliculaRepository.findAll();
    }

    public Pelicula createPelicula(Pelicula pelicula) {
        return peliculaRepository.save(pelicula);
    }

    public Pelicula findById(Long id) {
        return peliculaRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        peliculaRepository.deleteById(id);
    }
}
