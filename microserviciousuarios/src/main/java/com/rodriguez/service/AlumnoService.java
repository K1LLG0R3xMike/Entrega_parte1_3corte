package com.rodriguez.service;
import java.util.Optional;
import com.rodriguez.models.entity.Alumno;
public interface AlumnoService {
    public Iterable<Alumno> findAll();
    public Optional<Alumno> findById(Long id);
    public Alumno save(Alumno alumno);
    public void deleteById(Long id);

}
