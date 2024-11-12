package com.rodriguez.controller;

import com.rodriguez.models.entity.Alumno;
import com.rodriguez.service.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class AlumnoController {

    @Autowired
    private AlumnoService alumnoService;

    // Obtener todos los alumnos
    @GetMapping
    public ResponseEntity<?> listarAlumnos() {
        return ResponseEntity.ok().body(alumnoService.findAll());
    }

    // Obtener un alumno por ID
    @GetMapping("/")
    public ResponseEntity<?> obtenerAlumnoPorId(@PathVariable Long id) {
        Optional<Alumno> alumno = alumnoService.findById(id);
        if(alumno.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(alumno.get());

    }

    // Crear un nuevo alumno
    @PostMapping("/crear")
    public ResponseEntity<?> crearAlumno(@RequestBody Alumno alumno) {
        Alumno nuevoAlumno = alumnoService.save(alumno);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoAlumno);
    }

    // Actualizar un alumno existente
    @PutMapping("/{id}")
    public ResponseEntity<Alumno> actualizarAlumno(@PathVariable Long id, @RequestBody Alumno alumnoDetalles) {
        Optional<Alumno> alumno = alumnoService.findById(id);
        if (alumno.isPresent()) {
            Alumno alumnoExistente = alumno.get();
            alumnoExistente.setNombre(alumnoDetalles.getNombre());
            alumnoExistente.setApellido(alumnoDetalles.getApellido());
            alumnoExistente.setEmail(alumnoDetalles.getEmail());
            Alumno alumnoActualizado = alumnoService.save(alumnoExistente);
            return ResponseEntity.status(HttpStatus.CREATED).body(alumnoService.save(alumnoActualizado));
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    // Eliminar un alumno
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAlumno(@PathVariable Long id) {
        if (alumnoService.findById(id).isPresent()) {
            alumnoService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
