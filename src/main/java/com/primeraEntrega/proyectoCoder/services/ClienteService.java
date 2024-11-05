package com.primeraEntrega.proyectoCoder.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.primeraEntrega.proyectoCoder.dto.ClienteDTO;
import com.primeraEntrega.proyectoCoder.mapper.ClienteMapper;
import com.primeraEntrega.proyectoCoder.model.Cliente;
import com.primeraEntrega.proyectoCoder.repository.ClienteRepository;

@Service
public class ClienteService {

    private static final String API_URL = "https://jsonplaceholder.typicode.com/users";

    @Autowired
    private ClienteRepository clienteRepository;
    
    @Autowired
    private ClienteMapper clienteMapper;

    @Autowired
    private RestTemplate restTemplate;

    // Constructor
    public ClienteService(ClienteRepository clienteRepository, ClienteMapper clienteMapper, RestTemplate restTemplate) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
        this.restTemplate = restTemplate;
    }

    // POST PARA LA API
    public ResponseEntity<ClienteDTO> getClienteById(Long id) {
        ClienteDTO clienteDTO = restTemplate.getForObject(API_URL + "/{id}", ClienteDTO.class, id);

        if (clienteRepository.existsByName(clienteDTO.getName())) {
            return ResponseEntity.status(409).body(null);
        } else {
            if (this.clienteRepository.findById(id).isEmpty()) {
                this.clienteRepository.save(this.clienteMapper.toEntity(clienteDTO));
                return ResponseEntity.status(200).body(clienteDTO);
            }
        }

        throw new IllegalArgumentException("El cliente ya existe");
    }

    // POST PARA CREAR UN USUARIO DE MANERA MANUAL
    public ResponseEntity<ClienteDTO> crearUsuario(ClienteDTO clienteDTO) {
        if (clienteRepository.existsByName(clienteDTO.getName())) {
            return ResponseEntity.status(409).body(null);
        }

        Cliente cliente = clienteMapper.toEntity(clienteDTO);
        Cliente clienteGuardado = clienteRepository.save(cliente);

        return ResponseEntity.status(201).body(clienteMapper.toDTO( clienteGuardado));
    }

    // GET DE LA API
    public ClienteDTO[] traerClientesApi() {
        return restTemplate.getForObject(API_URL,  ClienteDTO[].class);
    }

    // GET DE LA BASE DE DATOS
    public List<ClienteDTO> mostrarClientesDB() {
        return this.clienteRepository.findAll()
        .stream().map(clienteMapper::toDTO)
        .collect(Collectors.toList());
    }

    // DELETE PARA CLIENTE EN BASE DE DATOS
    public void deleteById(Long id) {
        if (clienteRepository.existsById(id)){
            clienteRepository.deleteById(id);
        } else {
            throw new RuntimeException("Cliente no encontrado en la BD con ID: " + id);
        }
    }

    // PUT 
    public ClienteDTO actualizarCliente(Long id, ClienteDTO clienteDTO) {
        Cliente clienteExistente = clienteRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        clienteExistente.setName(clienteDTO.getName());
        clienteExistente.setEmail(clienteDTO.getEmail());
        clienteExistente.setPhone(clienteDTO.getPhone());
        clienteRepository.save(clienteExistente);
        return clienteMapper.toDTO(clienteRepository.save(clienteExistente));

    }

}
