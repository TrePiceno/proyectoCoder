package com.primeraEntrega.proyectoCoder.mapper;

import org.springframework.stereotype.Component;

import com.primeraEntrega.proyectoCoder.dto.ClienteDTO;
import com.primeraEntrega.proyectoCoder.model.Cliente;

@Component
public class ClienteMapper {

    public ClienteDTO toDTO(Cliente cliente) {

        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(cliente.getId());
        clienteDTO.setName(cliente.getName());
        clienteDTO.setEmail(cliente.getEmail());
        clienteDTO.setPhone(cliente.getPhone());
        return clienteDTO;
    }

    public Cliente toEntity(ClienteDTO clienteDTO) {

        Cliente cliente = new Cliente();
        cliente.setId(clienteDTO.getId());
        cliente.setName(clienteDTO.getName());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setPhone(clienteDTO.getPhone());
        return cliente;
    }

}
