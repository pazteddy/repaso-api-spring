package com.repaso.demo.alumno.repository;


import com.repaso.demo.alumno.domain.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlumnoRepository extends JpaRepository<Alumno, Long> {
    Optional<Alumno> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByDocumento(String documento);
}