package com.curso.controller;

import com.curso.model.Artista;
import com.curso.service.ArtistaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gestionar los endpoints relacionados con los artistas.
 * 
 * @author Victor Tercero
 * @version: 1.21
 */
@RestController
@RequestMapping("/artistas")
public class ArtistaController {

    @Autowired
    private ArtistaService artistaService;

    /**
     * Obtiene una lista de todos los artistas disponibles.
     *
     * @return Lista de artistas disponibles.
     */
    @Operation(summary = "Obtener todos los artistas disponibles", description = "Retorna una lista de artistas que están disponibles.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de artistas disponible", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Artista.class)))
    })
    @GetMapping
    public List<Artista> obtenerArtistasDisponibles() {
        return artistaService.obtenerArtistasDisponibles();
    }

    /**
     * Obtiene los detalles de un artista específico por su nombre.
     *
     * @param nombre El nombre del artista a buscar.
     * @return ResponseEntity con los detalles del artista si existe, o Not Found si no.
     */
    @Operation(summary = "Obtener un artista por nombre", description = "Retorna los detalles de un artista específico si existe.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Artista encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Artista.class))),
            @ApiResponse(responseCode = "404", description = "Artista no encontrado")
    })
    @GetMapping("/{nombre}")
    public ResponseEntity<Artista> obtenerArtistaPorNombre(@PathVariable String nombre) {
        Artista artista = artistaService.obtenerArtistaPorNombre(nombre);
        return artista != null ? ResponseEntity.ok(artista) : ResponseEntity.notFound().build();
    }

    /**
     * Obtiene los detalles de un artista específico por su ID.
     *
     * @param id El ID del artista a buscar.
     * @return ResponseEntity con los detalles del artista si existe, o Not Found si no.
     */
    @Operation(summary = "Obtener un artista por ID", description = "Retorna los detalles de un artista específico por su ID si existe.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Artista encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Artista.class))),
            @ApiResponse(responseCode = "404", description = "Artista no encontrado")
    })
    @GetMapping("/id/{id}")
    public ResponseEntity<Artista> obtenerArtistaPorId(@PathVariable Long id) {
        Artista artista = artistaService.obtenerArtistaPorId(id);
        return artista != null ? ResponseEntity.ok(artista) : ResponseEntity.notFound().build();
    }

    /**
     * Verifica la disponibilidad de un artista específico por su ID.
     *
     * @param id El ID del artista a verificar.
     * @return true si el artista está disponible, false si no.
     */
    @Operation(summary = "Verificar disponibilidad de un artista", description = "Verifica si un artista específico está disponible.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Disponibilidad del artista obtenida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Boolean.class))),
            @ApiResponse(responseCode = "404", description = "Artista no encontrado")
    })
    @GetMapping("/{id}/disponible")
    public ResponseEntity<Boolean> verificarDisponibilidad(@PathVariable Long id) {
        Artista artista = artistaService.obtenerArtistaPorId(id);
        if (artista != null) {
            return ResponseEntity.ok(artista.getDisponible());
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Crea un nuevo artista.
     *
     * @param artista El objeto Artista a crear.
     * @return El artista creado con estado 201 Created.
     */
    @Operation(summary = "Crear un nuevo artista", description = "Guarda un nuevo artista en la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Artista creado exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Artista.class)))
    })
    @PostMapping
    public ResponseEntity<Artista> crearArtista(@RequestBody Artista artista) {
        Artista nuevoArtista = artistaService.guardarArtista(artista);
        return ResponseEntity.status(201).body(nuevoArtista);
    }
}
