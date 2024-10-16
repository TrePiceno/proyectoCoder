package com.primeraEntrega.proyectoCoder.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.primeraEntrega.proyectoCoder.model.Almacen;

@Repository
public interface AlmacenRepository extends JpaRepository<Almacen, Long>{

    List<Almacen> findByNombre(String nombre);

}
