package com.primeraEntrega.proyectoCoder.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.primeraEntrega.proyectoCoder.dto.ClienteDTO;
import com.primeraEntrega.proyectoCoder.services.ClienteService;
import com.primeraEntrega.proyectoCoder.utils.ApiResponseMsg;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/cliente")
@Tag(name = "User", description = "API Test Swagger")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

     // POST PARA LA API
    @PostMapping(path = "/guardar/{id}")
    @Operation(summary = "Guardar cliente por id desde API externa", description = "Endpoint donde se trae y se guarda a un cliente de la API por su id y se guarda en la BD")
    public ResponseEntity<?> getClienteById(@PathVariable("id") Long id) {
        try {
            ResponseEntity<ClienteDTO> cliente = clienteService.getClienteById(id);
            return ResponseEntity.ok(new ApiResponseMsg("ok", cliente));
        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponseMsg("Error", e.getMessage()));
        }
    }

    // POST PARA CREAR UN USUARIO DE MANERA MANUAL
    @PostMapping(path = "/crearCliente")
    @Operation(summary = "Crear cliente de manera manual", description = "Se ingresan los datos del cliente para que se guarde en la BD")
    public ResponseEntity<ClienteDTO> crearUsuario(@RequestBody ClienteDTO clienteDTO) {
        return clienteService.crearUsuario(clienteDTO);
    }

    // GET DE LA API
    @GetMapping(path = "/all")
    @Operation(summary = "Mostrar todos los clientes de la API externa", description = "Endpoint donde se muestran a todos los clientes de la API")
    public ResponseEntity<?> getAllClientes() {
        try {
            ClienteDTO[] clientes = clienteService.traerClientesApi();
            return ResponseEntity.ok(new ApiResponseMsg("ok", clientes));
        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponseMsg("Error", e.getMessage()));
        }
    }

    // GET DE LA BASE DE DATOS
    @GetMapping(path = "/allDB")
    @Operation(summary = "Mostrar todos los clientes de la base de datos", description = "Endpoint donde se muestran a todos los clientes guardados en la base de datos")
    public ResponseEntity<?> getAllClientesDB() {
        try {
            List<ClienteDTO> clientes = clienteService.mostrarClientesDB();
            return ResponseEntity.ok(new ApiResponseMsg("ok", clientes));
        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponseMsg("Error", e.getMessage()));
        }
    }
    
    // DELETE PARA CLIENTE EN BASE DE DATOS
    @DeleteMapping("/borrar/{id}")
    @Operation(summary = "Borrar un usuario de la DB", description = "Borra un usuario por su ID")
    public ResponseEntity<?> borrarCliente(
        @Parameter(description = "ID of the user to be deleted") @PathVariable Long id) {
            try {
                clienteService.deleteById(id);
                return ResponseEntity.ok(new ApiResponseMsg("ID del cliente eliminado", id));
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(new ApiResponseMsg("User not found", e.getMessage()));
            }
        }

    // PUT
    @PutMapping("/actualizar/{id}")
    @Operation(summary = "Actualiza datos de un cliente", description = "Modifica todos las propiedades de un cliente en la base de datos")
    public ResponseEntity<ClienteDTO> actualizarCliente(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO){
        ClienteDTO updatedCliente = clienteService.actualizarCliente(id, clienteDTO);
        return ResponseEntity.ok(updatedCliente);
    }
    
}
