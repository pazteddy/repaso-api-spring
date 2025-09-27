package com.repaso.demo.alumno.web;

import com.repaso.demo.alumno.domain.dto.*;
import com.repaso.demo.alumno.service.AlumnoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/alumnos")
public class AlumnoController {

    private final AlumnoService service;

    @Autowired
    public AlumnoController(AlumnoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<AlumnoResponseDTO>> listar(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id,asc") String sort) {

        String[] sortParts = sort.split(",");
        Sort s = sortParts.length == 2 && sortParts[1].equalsIgnoreCase("desc")
                ? Sort.by(sortParts[0]).descending()
                : Sort.by(sortParts[0]).ascending();
        Pageable pageable = PageRequest.of(page, size, s);
        return ResponseEntity.ok(service.listar(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlumnoResponseDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<AlumnoResponseDTO> crear(@Valid @RequestBody AlumnoCreateDTO dto) {
        AlumnoResponseDTO created = service.crear(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlumnoResponseDTO> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody AlumnoUpdateDTO dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
