package com.repaso.demo.cliente;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }
    @GetMapping
    public List<Cliente> listar()
    {
        return clienteService.listarClientes();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtener(@PathVariable Long id) {
        return clienteService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Cliente crear(@RequestBody Cliente cliente) {
        return clienteService.guardar(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizar(@PathVariable Long id, @RequestBody Cliente cliente) {
        return clienteService.obtenerPorId(id)
                .map(c -> {
                    cliente.setId(id);
                    return ResponseEntity.ok(clienteService.guardar(cliente));
                })
                .orElse(ResponseEntity.notFound().build());
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Cliente> actualizarParcial(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return clienteService.obtenerPorId(id)
                .map(cliente -> {
                    updates.forEach((key, value) -> {
                        switch (key) {
                            case "nombre":
                                cliente.setNombre((String)value);
                                break;
                            case "direccion":
                                cliente.setDireccion((String)value);
                                break;
                            case "email":
                                cliente.setEmail((String)value);
                                break;
                            // Agrega más campos si es necesario
                        }
                    });
                    return ResponseEntity.ok(clienteService.guardar(cliente));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (clienteService.obtenerPorId(id).isPresent()) {
            clienteService.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
