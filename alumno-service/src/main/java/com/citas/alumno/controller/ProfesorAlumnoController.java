package com.citas.alumno.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


    @RestController
    @RequestMapping("/prof")
    public class ProfesorAlumnoController {

    @GetMapping("/list")
    public ResponseEntity<List<String>> listarProfesores() {
        List<String> profesores = Arrays.asList(
                "Profesor Juan Pérez",
                "Profesor María López",
                "Profesor Carlos Ramírez"
        );
        return ResponseEntity.ok(profesores);
    }
}
