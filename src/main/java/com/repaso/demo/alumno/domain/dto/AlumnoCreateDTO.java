package com.repaso.demo.alumno.domain.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class AlumnoCreateDTO {

    @NotBlank(message = "Los nombres son obligatorios")
    @Size(max = 100, message = "Nombres: máximo 100 caracteres")
    private String nombres;

    @NotBlank(message = "Los apellidos son obligatorios")
    @Size(max = 100, message = "Apellidos: máximo 100 caracteres")
    private String apellidos;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "Formato de email inválido")
    @Size(max = 150, message = "Email: máximo 150 caracteres")
    private String email;

    // Validación de negocio (p.ej., edad) se sugiere en la capa de servicio
    private LocalDate fechaNacimiento;

    @Size(max = 30, message = "Documento: máximo 30 caracteres")
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
