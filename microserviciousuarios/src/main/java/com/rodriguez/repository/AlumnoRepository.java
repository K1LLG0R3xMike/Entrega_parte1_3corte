package com.rodriguez.repository;

import com.rodriguez.models.entity.Alumno;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlumnoRepository extends CrudRepository<Alumno, Long> {
    // Puedes añadir métodos personalizados si los necesitas
}
