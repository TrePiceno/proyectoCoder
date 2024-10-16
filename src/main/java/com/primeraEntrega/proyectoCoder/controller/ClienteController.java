package com.primeraEntrega.proyectoCoder.controller;

import java.util.List;
// import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.primeraEntrega.proyectoCoder.model.Cliente;
import com.primeraEntrega.proyectoCoder.services.ClienteService;
import com.primeraEntrega.proyectoCoder.utils.ApiResponse;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/all")
    public List<Cliente> getAllClient() {
        return this.clienteService.mostrarClientes();
    }

    @GetMapping("/mostrarPorId/{id}")
    public ResponseEntity<?> buscarPersonaPorApellido(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(new ApiResponse("Mostrando cliente por ID", this.clienteService.mostrarPorId(id)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> agregarPersona(@RequestBody Cliente cliente) {
        try {
            this.clienteService.crearCliente(cliente);
            return ResponseEntity.ok().body(new ApiResponse("Nuevo cliente creado con éxito", cliente));

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage() + "!!");
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PutMapping("modificar/{id}")
    public ResponseEntity<?> modificarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        try {
            Cliente clienteActualizado = clienteService.actualizarCliente(id, cliente);
            return ResponseEntity.ok().body(new ApiResponse("El cliente ha sido modificado con éxito", clienteActualizado));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(new ApiResponse("Datos de cliente inválidos: ", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(404)
                    .body(new ApiResponse("Error inesperado", "Error al actualizar el cliente"));
        }
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> borrarClienteId(@PathVariable Long id) {
        try {
            this.clienteService.deleteCliente(id);
            return ResponseEntity.ok().body(new ApiResponse("El cliente ha sido eliminado con éxito", id));

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage() + "!!");
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

}
