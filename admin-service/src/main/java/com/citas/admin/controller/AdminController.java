package com.citas.admin.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final RestTemplate restTemplate;
    @Value("${alumno.url}") private String alumnoUrl;

    public AdminController(RestTemplate restTemplate){ this.restTemplate = restTemplate; }

    // Listar todas las citas (proxy)
    @GetMapping("/citas")
    public ResponseEntity<?> listar() {
        String url = alumnoUrl + "/citas";
        ResponseEntity<Object[]> resp = restTemplate.getForEntity(url, Object[].class);
        return ResponseEntity.status(resp.getStatusCode()).body(Arrays.asList(resp.getBody()));
    }

    // Eliminar cita
    @DeleteMapping("/citas/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        String url = alumnoUrl + "/citas/" + id;
        ResponseEntity<Void> resp = restTemplate.exchange(url, HttpMethod.DELETE, null, Void.class);
        return ResponseEntity.status(resp.getStatusCode()).build();
    }
}
