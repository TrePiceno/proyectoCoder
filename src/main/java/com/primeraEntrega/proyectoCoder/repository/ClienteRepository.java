package com.primeraEntrega.proyectoCoder.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.primeraEntrega.proyectoCoder.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
    List<Cliente> findByName(Cliente cliente);

    boolean existsByName(String nombre);
}
