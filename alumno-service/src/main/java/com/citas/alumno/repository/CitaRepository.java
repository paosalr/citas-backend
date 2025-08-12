package com.citas.alumno.repository;

import com.citas.alumno.model.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CitaRepository extends JpaRepository<Cita, Long> {
    List<Cita> findByProfesor(String profesor);
    List<Cita> findByAlumno(String alumno);
}
