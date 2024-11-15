package com.curso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.curso.model.Reserva;
import com.curso.service.ReservaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 * Controlador REST para gestionar los endpoints relacionados con las reservas.
 * 
 * @author Victor Tercero
 * @version: 1.21
 */
@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @Autowired
    private RestTemplate restTemplate;

    private String eventosServiceUrl= "http://localhost:8081";

    private String artistasServiceUrl = "http://localhost:8082";

    /**
     * Crea una nueva reserva verificando la disponibilidad del evento y del artista.
     *
     * @param reserva Objeto Reserva con los datos de la nueva reserva.
     * @return La reserva creada si el evento y el artista están disponibles; de lo contrario, un error 400.
     */
    @Operation(summary = "Crear una nueva reserva", description = "Guarda una nueva reserva en la base de datos si el evento y el artista están disponibles.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reserva creada exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Reserva.class))),
            @ApiResponse(responseCode = "400", description = "Evento o artista no disponible")
    })
    @PostMapping
    public ResponseEntity<Reserva> crearReserva(@RequestBody Reserva reserva) {
    	String eventoUrl = eventosServiceUrl + "/eventos/id/" + reserva.getIdEvento() + "/disponible";
    	String artistaUrl = artistasServiceUrl + "/artistas/id/" + reserva.getIdArtista() + "/disponible";

    	// Verificar disponibilidad del evento y del artista
    	ResponseEntity<Boolean> eventoDisponible = restTemplate.getForEntity(eventoUrl, Boolean.class);
    	ResponseEntity<Boolean> artistaDisponible = restTemplate.getForEntity(artistaUrl, Boolean.class);
    	if (Boolean.FALSE.equals(eventoDisponible.getBody()) || Boolean.FALSE.equals(artistaDisponible.getBody())) {
    	    return ResponseEntity.badRequest().body(null);
    	}


    	Reserva nuevaReserva = reservaService.crearReserva(reserva);
    	return ResponseEntity.ok(nuevaReserva);

    }

    /**
     * Obtiene todas las reservas asociadas a un evento específico.
     *
     * @param idEvento ID del evento para el que se desean obtener las reservas.
     * @return Lista de reservas asociadas al evento especificado.
     */
    @Operation(summary = "Obtener reservas por evento", description = "Retorna una lista de reservas asociadas a un evento específico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de reservas para el evento", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Reserva.class)))
    })
    @GetMapping("/evento/{idEvento}")
    public List<Reserva> obtenerReservasPorEvento(@PathVariable Long idEvento) {
        return reservaService.obtenerReservasPorEvento(idEvento);
    }
}
