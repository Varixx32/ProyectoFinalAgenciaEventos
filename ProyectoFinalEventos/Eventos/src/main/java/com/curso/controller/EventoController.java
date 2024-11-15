package com.curso.controller;

import com.curso.model.Evento;
import com.curso.service.EventoService;
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
 * Controlador REST para gestionar los endpoints relacionados con los eventos.
 * 
 * @author Victor Tercero
 * @version: 1.21
 */
@RestController
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    /**
     * Obtiene una lista de todos los eventos disponibles.
     *
     * @return Lista de eventos disponibles.
     */
    @Operation(summary = "Obtener todos los eventos disponibles", description = "Retorna una lista de eventos que están disponibles.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de eventos disponible", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Evento.class)))
    })
    @GetMapping
    public List<Evento> obtenerEventosDisponibles() {
        return eventoService.obtenerEventosDisponibles();
    }

    /**
     * Obtiene los detalles de un evento específico por su nombre.
     *
     * @param nombre El nombre del evento a buscar.
     * @return ResponseEntity con los detalles del evento si existe, o Not Found si no.
     */
    @Operation(summary = "Obtener un evento por nombre", description = "Retorna los detalles de un evento específico si existe.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Evento encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Evento.class))),
            @ApiResponse(responseCode = "404", description = "Evento no encontrado")
    })
    @GetMapping("/{nombre}")
    public ResponseEntity<Evento> obtenerEventoPorNombre(@PathVariable String nombre) {
        Evento evento = eventoService.obtenerEventoPorNombre(nombre);
        return evento != null ? ResponseEntity.ok(evento) : ResponseEntity.notFound().build();
    }

    /**
     * Obtiene los detalles de un evento específico por su ID.
     *
     * @param id El ID del evento a buscar.
     * @return ResponseEntity con los detalles del evento si existe, o Not Found si no.
     */
    @Operation(summary = "Obtener un evento por ID", description = "Retorna los detalles de un evento específico por su ID si existe.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Evento encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Evento.class))),
            @ApiResponse(responseCode = "404", description = "Evento no encontrado")
    })
    @GetMapping("/id/{id}")
    public ResponseEntity<Evento> obtenerEventoPorId(@PathVariable Long id) {
        Evento evento = eventoService.obtenerEventoPorId(id);
        return evento != null ? ResponseEntity.ok(evento) : ResponseEntity.notFound().build();
    }

    /**
     * Verifica la disponibilidad de un evento específico por su ID.
     *
     * @param id El ID del evento a verificar.
     * @return true si el evento está disponible, false si no.
     */
    @Operation(summary = "Verificar disponibilidad de un evento", description = "Verifica si un evento específico está disponible.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Disponibilidad del evento obtenida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Boolean.class))),
            @ApiResponse(responseCode = "404", description = "Evento no encontrado")
    })
    @GetMapping("/{id}/disponible")
    public ResponseEntity<Boolean> verificarDisponibilidad(@PathVariable Long id) {
        Evento evento = eventoService.obtenerEventoPorId(id);
        if (evento != null) {
            return ResponseEntity.ok(evento.getDisponible());
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Crea un nuevo evento.
     *
     * @param evento El objeto Evento a crear.
     * @return El evento creado con estado 201 Created.
     */
    @Operation(summary = "Crear un nuevo evento", description = "Guarda un nuevo evento en la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Evento creado exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Evento.class)))
    })
    @PostMapping
    public ResponseEntity<Evento> crearEvento(@RequestBody Evento evento) {
        Evento nuevoEvento = eventoService.guardarEvento(evento);
        return ResponseEntity.status(201).body(nuevoEvento);
    }
}
