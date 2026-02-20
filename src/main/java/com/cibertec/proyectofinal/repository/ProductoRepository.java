package com.cibertec.proyectofinal.repository;

import com.cibertec.proyectofinal.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
