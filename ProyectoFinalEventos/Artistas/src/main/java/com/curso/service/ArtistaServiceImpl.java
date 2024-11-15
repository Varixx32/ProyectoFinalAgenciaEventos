package com.curso.service;

import com.curso.model.Artista;
import com.curso.repository.ArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementación de la interfaz ArtistaService.
 * 
 * @author Victor Tercero
 * @version: 1.21
 */
@Service
public class ArtistaServiceImpl implements ArtistaService {

    @Autowired
    private ArtistaRepository artistaRepository;

    /**
     * Obtiene una lista de todos los artistas disponibles.
     *
     * @return Lista de artistas disponibles.
     */
    @Override
    public List<Artista> obtenerArtistasDisponibles() {
        return artistaRepository.findByDisponibleTrue();
    }

    /**
     * Obtiene un artista específico por su nombre.
     *
     * @param nombre El nombre del artista.
     * @return El artista correspondiente al nombre o null si no existe.
     */
    @Override
    public Artista obtenerArtistaPorNombre(String nombre) {
        return artistaRepository.findByNombre(nombre);
    }

    /**
     * Obtiene un artista específico por su ID.
     *
     * @param id El ID del artista.
     * @return El artista correspondiente al ID o null si no existe.
     */
    @Override
    public Artista obtenerArtistaPorId(Long id) {
        return artistaRepository.findById(id).orElse(null);
    }

    /**
     * Guarda un nuevo artista en la base de datos.
     *
     * @param artista El artista a guardar.
     * @return El artista guardado.
     */
    @Override
    public Artista guardarArtista(Artista artista) {
        return artistaRepository.save(artista);
    }
}
