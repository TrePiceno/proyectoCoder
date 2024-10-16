package com.primeraEntrega.proyectoCoder.model;

import java.util.List;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "ALMACEN")
public class Almacen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOMBRE")
    private String nombre;

    // Me queda la duda de que el almacen no maneje la relación si el contiene a los productos y a las tiendas

    @OneToMany(mappedBy = "almacen", fetch = FetchType.EAGER)
    private List<Producto> productos;

    @OneToMany(mappedBy = "almacen", fetch = FetchType.EAGER)
    private List<Tienda> tiendas;

}
