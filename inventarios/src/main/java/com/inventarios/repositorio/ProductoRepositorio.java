package com.inventarios.repositorio;

import com.inventarios.modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepositorio extends JpaRepository<Producto, Integer> {
    // JpaRepository añade las funcionalidades CRUD
}
