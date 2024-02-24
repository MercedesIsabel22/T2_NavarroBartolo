package com.example.T2_NavarroBartolo.controller;

import com.example.T2_NavarroBartolo.model.Genero;
import com.example.T2_NavarroBartolo.model.Pelicula;
import com.example.T2_NavarroBartolo.service.GeneroService;
import com.example.T2_NavarroBartolo.service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/api/v1/pelicula")
public class PeliculaController {
    @Autowired
    private PeliculaService peliculaService;
    @Autowired
    private GeneroService generoService;
    @GetMapping("/peliculas")
    public String getAllProduct(Model model) {

        List<Pelicula> lisPeliculas = peliculaService.getAllPeliculas();

        for (Pelicula pelicula : lisPeliculas) {
            System.out.println(pelicula.getId());
            System.out.println(pelicula.getNombre());
            System.out.println(pelicula.getDirector());
            System.out.println(pelicula.getFechaEstreno());
            System.out.println(pelicula.getGenero().getId());
            System.out.println(pelicula.getGenero().getNombre());
        }
        model.addAttribute("peliculas", lisPeliculas);
        return "peliculaList";
    }
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("generos", generoService.getAllGeneros());
        return "PeliculaRegister";
    }
    @PostMapping("/save")
    public String save(@RequestParam ("pelicula")String pelicula, @RequestParam ("director")String director,
                         @RequestParam ("fecha") String fecha, @RequestParam ("idGenero")String genero, Model model) throws ParseException {
        Genero objGenero = generoService.findById(Long.valueOf(genero));
        Pelicula peliculas = new Pelicula();
        peliculas.setNombre(pelicula);
        peliculas.setDirector(director);
        SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
        Date fechaDate = formato.parse(fecha);
        peliculas.setFechaEstreno(fechaDate);
        peliculas.setGenero(objGenero);

        peliculaService.createPelicula(peliculas);
        List<Pelicula> listPeliculas = peliculaService.getAllPeliculas();
        model.addAttribute("peliculas", listPeliculas );
        return "peliculaList";

    }
    @GetMapping("/edit/{id}")
    public String getPeliculaByID(@PathVariable Long id, Model model) {
        Pelicula pelicula = peliculaService.findById(id);
        model.addAttribute("pelicula", pelicula);
        model.addAttribute("generos", generoService.getAllGeneros());
        return "peliculaEdit";
    }
    @PostMapping("/edit")
    public String editPelicula(@RequestParam("id") Long id, @RequestParam("pelicula") String nombre, @RequestParam("director") String director,
                               @RequestParam("idGenero") Long idGenero,  Model model) {
        Genero gnr = generoService.findById(idGenero);
        Pelicula obj = peliculaService.findById(id);
        obj.setNombre(nombre);
        obj.setDirector(director);
        obj.setGenero(gnr);

        peliculaService.createPelicula(obj);

        List<Pelicula> listPeliculas = peliculaService.getAllPeliculas();
        model.addAttribute("peliculas", listPeliculas );
        return "peliculaList";
    }
    @GetMapping("/delete/{id}")
    public String deletePelicula(@PathVariable Long id, Model model) {
        peliculaService.deleteById(id);

        List<Pelicula> listPelicula = peliculaService.getAllPeliculas();
        model.addAttribute("peliculas", listPelicula);

        return "peliculaList";
    }

}
