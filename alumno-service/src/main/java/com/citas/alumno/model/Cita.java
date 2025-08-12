package com.citas.alumno.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String alumno;          // nombre o id de alumno (string simple para ejemplo)
    @NotBlank
    private String profesor;        // nombre o id del profesor

    @NotBlank
    private String asunto;

    @Column(length = 2000)
    private String descripcion;

    private LocalDateTime fechaDeseada;

    private String estado = "PENDIENTE"; // PENDIENTE / ACEPTADA / RECHAZADA

    private LocalDateTime scheduledAt; // fecha/hora final asignada por profesor (si acepta)
    private String location;           // lugar asignado por profesor

    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() { createdAt = LocalDateTime.now(); }

    // Getters y setters
    public Long getId() { return id; }
    public String getAlumno() { return alumno; }
    public void setAlumno(String alumno) { this.alumno = alumno; }
    public String getProfesor() { return profesor; }
    public void setProfesor(String profesor) { this.profesor = profesor; }
    public String getAsunto() { return asunto; }
    public void setAsunto(String asunto) { this.asunto = asunto; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public LocalDateTime getFechaDeseada() { return fechaDeseada; }
    public void setFechaDeseada(LocalDateTime fechaDeseada) { this.fechaDeseada = fechaDeseada; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public LocalDateTime getScheduledAt() { return scheduledAt; }
    public void setScheduledAt(LocalDateTime scheduledAt) { this.scheduledAt = scheduledAt; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
