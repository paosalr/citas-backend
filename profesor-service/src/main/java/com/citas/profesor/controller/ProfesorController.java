package com.citas.profesor.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RestController
@RequestMapping("/prof")
public class ProfesorController {

    private final RestTemplate restTemplate;
    @Value("${alumno.url}") private String alumnoUrl; // http://localhost:8081

    public ProfesorController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Obtener solicitudes asignadas a un profesor (por nombre/id)
    @GetMapping("/appointments/{profesor}")
    public ResponseEntity<?> obtenerPorProfesor(@PathVariable String profesor) {
        String url = alumnoUrl + "/citas/profesor/" + profesor;
        ResponseEntity<Object[]> resp = restTemplate.getForEntity(url, Object[].class);
        return ResponseEntity.status(resp.getStatusCode()).body(Arrays.asList(resp.getBody()));
    }

    // Aceptar o rechazar y asignar fecha/lugar (envía PUT a alumno-service)
    @PutMapping("/appointments/{id}/status")
    public ResponseEntity<?> actualizarEstado(@PathVariable Long id, @RequestBody Object payload) {
        String url = alumnoUrl + "/citas/" + id + "/status";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> ent = new HttpEntity<>(payload, headers);
        ResponseEntity<Object> resp = restTemplate.exchange(url, HttpMethod.PUT, ent, Object.class);
        return ResponseEntity.status(resp.getStatusCode()).body(resp.getBody());
    }
    @GetMapping("/list")
    public ResponseEntity<?> obtenerListaProfesores() {
        String url = alumnoUrl + "/prof/list"; // coincide con alumno-service
        ResponseEntity<Object[]> resp = restTemplate.getForEntity(url, Object[].class);
        return ResponseEntity.status(resp.getStatusCode()).body(Arrays.asList(resp.getBody()));
    }


}
