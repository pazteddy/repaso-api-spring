package com.repaso.demo.alumno.domain.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class AlumnoUpdateDTO {

    @NotBlank @Size(max = 100)
    private String nombres;

    @NotBlank @Size(max = 100)
    private String apellidos;

    @NotBlank @Email @Size(max = 150)
    private String email;

    private LocalDate fechaNacimiento;

    @Size(max = 30)
    private String documento;

    // Getters y Setters
    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }
    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }
    public String getDocumento() { return documento; }
    public void setDocumento(String documento) { this.documento = documento; }
}