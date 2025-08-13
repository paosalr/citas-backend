package com.citas.alumno.controller;

import com.citas.alumno.model.Cita;
import com.citas.alumno.repository.CitaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/citas")
public class CitaController {

    private final CitaRepository repository;

    public CitaController(CitaRepository repository) {
        this.repository = repository;
    }

    // Crear cita (por alumno)
    @PostMapping
    public ResponseEntity<Cita> crear(@Valid @RequestBody Cita cita) {
        cita.setEstado("PENDIENTE");
        Cita saved = repository.save(cita);
        return ResponseEntity.ok(saved);
    }

    // Listar todas las citas
    @GetMapping
    public List<Cita> listar() {
        return repository.findAll();
    }

    // Listar por profesor
    @GetMapping("/profesor/{profesor}")
    public List<Cita> porProfesor(@PathVariable String profesor) {
        return repository.findByProfesor(profesor);
    }

    // Listar por alumno
    @GetMapping("/alumno/{alumno}")
    public List<Cita> porAlumno(@PathVariable String alumno) {
        return repository.findByAlumno(alumno);
    }

    // Obtener por id
    @GetMapping("/{id}")
    public ResponseEntity<Cita> get(@PathVariable Long id) {
        Optional<Cita> opt = repository.findById(id);
        return opt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Actualizar estado y detalles (usado por profesor-service)
    @PutMapping("/{id}/status")
    public ResponseEntity<Cita> actualizarEstado(@PathVariable Long id, @RequestBody Cita incoming) {
        Optional<Cita> opt = repository.findById(id);
        if (opt.isEmpty()) return ResponseEntity.notFound().build();
        Cita c = opt.get();
        if (incoming.getEstado() != null) c.setEstado(incoming.getEstado());
        if (incoming.getScheduledAt() != null) c.setScheduledAt(incoming.getScheduledAt());
        if (incoming.getLocation() != null) c.setLocation(incoming.getLocation());
        repository.save(c);
        return ResponseEntity.ok(c);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cita> actualizarCita(@PathVariable Long id, @RequestBody Cita datosCita) {
        return repository.findById(id)
            .map(cita -> {
                cita.setAlumno(datosCita.getAlumno());
                cita.setProfesor(datosCita.getProfesor());
                cita.setAsunto(datosCita.getAsunto());
                cita.setDescripcion(datosCita.getDescripcion());
                cita.setFechaDeseada(datosCita.getFechaDeseada());
                cita.setEstado(datosCita.getEstado());
                cita.setScheduledAt(datosCita.getScheduledAt());
                cita.setLocation(datosCita.getLocation());
                Cita citaActualizada = repository.save(cita);
                return ResponseEntity.ok(citaActualizada);
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Eliminar cita (usado por admin)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
