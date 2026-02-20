package com.cibertec.proyectofinal.service;

import com.cibertec.proyectofinal.model.Producto;

import java.util.List;

public interface ProductoService {
    List<Producto> listar();
    Producto buscarPorId(Long id);
    Producto registrar(Producto producto);
    Producto actualizar(Long id, Producto producto);
    void eliminar(Long id);
}
