package com.primeraEntrega.proyectoCoder.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.primeraEntrega.proyectoCoder.model.Tienda;

@Repository
public interface TiendaRepository extends JpaRepository<Tienda, Long> {

    List<Tienda> findByNombre(String nombre);
}
