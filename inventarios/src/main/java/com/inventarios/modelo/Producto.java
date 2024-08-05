package com.inventarios.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data // Crea los getter y setter
@NoArgsConstructor // Genera constructor sin argumentos
@AllArgsConstructor
@ToString // Genera el metodo toString
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Id sera generado automaticamente (autoincrementable)
    Integer idProducto;
    String descripcion;
    Double precio;
    Integer existencia;
}
