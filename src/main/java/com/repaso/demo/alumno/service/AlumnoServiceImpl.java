package com.repaso.demo.alumno.service;


import com.repaso.demo.alumno.domain.Alumno;
import com.repaso.demo.alumno.domain.dto.*;
import com.repaso.demo.alumno.repository.AlumnoRepository;
import com.repaso.demo.alumno.web.error.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AlumnoServiceImpl implements AlumnoService {

    private final AlumnoRepository repository;

    @Autowired
    public AlumnoServiceImpl(AlumnoRepository repository) {
        this.repository = repository;
    }

    private AlumnoResponseDTO toResponse(Alumno a) {
        AlumnoResponseDTO dto = new AlumnoResponseDTO();
        dto.setId(a.getId());
        dto.setNombres(a.getNombres());
        dto.setApellidos(a.getApellidos());
        dto.setEmail(a.getEmail());
        dto.setFechaNacimiento(a.getFechaNacimiento());
        dto.setDocumento(a.getDocumento());
        return dto;
    }

    private void ensureUnique(String email, String documento, Long excludeId) {
        if (email != null) {
            boolean emailTaken = repository.existsByEmail(email)
                    && (excludeId == null || repository.findByEmail(email)
                    .map(al -> !al.getId().equals(excludeId)).orElse(false));
            if (emailTaken) {
                throw new DuplicatedResourceException("El email ya está registrado");
            }
        }
        if (documento != null && repository.existsByDocumento(documento)) {
            if (excludeId == null) {
                throw new DuplicatedResourceException("El documento ya está registrado");
            }
            // Si es actualización, permitir mismo documento del propio registro
            repository.findById(excludeId).ifPresent(existing -> {
                if (existing.getDocumento() != null && !existing.getDocumento().equals(documento)) {
                    throw new DuplicatedResourceException("El documento ya está registrado");
                }
            });
        }
    }

    @Override
    @Transactional
    public AlumnoResponseDTO crear(AlumnoCreateDTO dto) {
        ensureUnique(dto.getEmail(), dto.getDocumento(), null);
        Alumno a = new Alumno();
        a.setNombres(dto.getNombres());
        a.setApellidos(dto.getApellidos());
        a.setEmail(dto.getEmail());
        a.setFechaNacimiento(dto.getFechaNacimiento());
        a.setDocumento(dto.getDocumento());
        a = repository.save(a);
        return toResponse(a);
    }

    @Override
    @Transactional
    public AlumnoResponseDTO actualizar(Long id, AlumnoUpdateDTO dto) {
        Alumno a = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Alumno no encontrado"));
        // Validar unicidad (permitiendo que el propio registro conserve sus datos)
        ensureUnique(dto.getEmail(), dto.getDocumento(), id);

        a.setNombres(dto.getNombres());
        a.setApellidos(dto.getApellidos());
        a.setEmail(dto.getEmail());
        a.setFechaNacimiento(dto.getFechaNacimiento());
        a.setDocumento(dto.getDocumento());
        repository.save(a);
        return toResponse(a);
    }

    @Override
    @Transactional(readOnly = true)
    public AlumnoResponseDTO obtenerPorId(Long id) {
        Alumno a = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Alumno no encontrado"));
        return toResponse(a);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AlumnoResponseDTO> listar(Pageable pageable) {
        return repository.findAll(pageable).map(this::toResponse);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Alumno no encontrado");
        }
        repository.deleteById(id);
    }
}