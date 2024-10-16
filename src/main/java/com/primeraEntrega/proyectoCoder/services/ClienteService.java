package com.primeraEntrega.proyectoCoder.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.primeraEntrega.proyectoCoder.model.Cliente;
import com.primeraEntrega.proyectoCoder.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    // Traernos a todos los clientes
    public List<Cliente> mostrarClientes() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> mostrarPorId(Long id) {
        return this.clienteRepository.findById(id);
    }

    // Crear y guardar clientes
    public void crearCliente(Cliente c) {
        this.clienteRepository.save(c);
    }

    // Actualizar cliente
    public Cliente actualizarCliente(Long id, Cliente c) {
        Cliente clienteExistente = this.clienteRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));

        clienteExistente.setNombre(c.getNombre());
        clienteExistente.setApellido(c.getApellido());
        clienteExistente.setCorreo(c.getCorreo());
        clienteExistente.setTelefono(c.getTelefono());

        return this.clienteRepository.save(clienteExistente);
    }

    // Borrar cliente
    public void deleteCliente(Long id){
        if (clienteRepository.existsById(id)){
            clienteRepository.deleteById(id);
        } else {
            System.out.println("El cliente no existe");
        }
    }

    // Con este metodo se borra un cliente guardado en una variable
    // public void borrarCliente(Cliente c) {
    //     if (this.clienteRepository.existsById(c.getId())) {
    //         this.clienteRepository.delete(c);
    //     } else {
    //         System.out.println("El cliente no existe");
    //     }
    // }

}
