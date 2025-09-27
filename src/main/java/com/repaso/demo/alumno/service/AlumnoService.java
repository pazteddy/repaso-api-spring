package com.repaso.demo.alumno.service;

import com.repaso.demo.alumno.domain.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AlumnoService {
    AlumnoResponseDTO crear(AlumnoCreateDTO dto);
    AlumnoResponseDTO actualizar(Long id, AlumnoUpdateDTO dto);
    AlumnoResponseDTO obtenerPorId(Long id);
    Page<AlumnoResponseDTO> listar(Pageable pageable);
    void eliminar(Long id);
}