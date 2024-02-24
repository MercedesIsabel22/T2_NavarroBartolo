package com.example.T2_NavarroBartolo.service;

import com.example.T2_NavarroBartolo.model.Genero;
import com.example.T2_NavarroBartolo.repository.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneroService {
    @Autowired
    private GeneroRepository generoRepository;

    public GeneroService(GeneroRepository generoRepository) {
        this.generoRepository = generoRepository;
    }


    public Genero save(Genero genero) {
        return generoRepository.save(genero);
    }
    public List<Genero> getAllGeneros(){
        return generoRepository.findAll();
    }

    public Genero findById(Long id) {
        return generoRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        generoRepository.deleteById(id);
    }

}
