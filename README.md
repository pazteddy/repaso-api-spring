# Demo Spring Boot

Este proyecto es una aplicación de ejemplo desarrollada con Spring Boot. Permite gestionar entidades como alumnos y clientes, implementando operaciones CRUD y manejo de excepciones personalizadas.

## Estructura principal
- **Alumno**: Gestión de alumnos (crear, actualizar, listar, eliminar).
- **Cliente**: Gestión de clientes.
- **Controladores REST**: Exponen las APIs para interactuar con las entidades.
- **Servicios y repositorios**: Lógica de negocio y acceso a datos.

## Requisitos
- Java 17 o superior
- Maven

## Cómo levantar el proyecto

1. **Clona el repositorio o descarga el código fuente.**
2. **Compila y ejecuta el proyecto:**
   
   Abre una terminal en la raíz del proyecto y ejecuta:
   
   ```cmd
   mvnw.cmd spring-boot:run
   ```
   
   O si tienes Maven instalado globalmente:
   
   ```cmd
   mvn spring-boot:run
   ```
3. **Accede a la aplicación:**
   
   Por defecto, la aplicación se ejecuta en [http://localhost:8080](http://localhost:8080)

## Endpoints principales para probar

### Alumno
- `GET /alumnos` — Listar todos los alumnos
- `GET /alumnos/{id}` — Obtener un alumno por ID
- `POST /alumnos` — Crear un nuevo alumno
- `PUT /alumnos/{id}` — Actualizar un alumno existente
- `DELETE /alumnos/{id}` — Eliminar un alumno

### Cliente
- `GET /clientes` — Listar todos los clientes
- `GET /clientes/{id}` — Obtener un cliente por ID
- `POST /clientes` — Crear un nuevo cliente
- `PUT /clientes/{id}` — Actualizar un cliente existente
- `DELETE /clientes/{id}` — Eliminar un cliente

## Notas
- Puedes modificar la configuración en `src/main/resources/application.properties`.
- Los endpoints principales están documentados en los controladores dentro de `src/main/java/com/repaso/demo/alumno/web/` y `src/main/java/com/repaso/demo/cliente/`.

## Contacto
Para dudas o sugerencias, contacta al autor del repositorio.
