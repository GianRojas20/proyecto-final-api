package com.cibertec.proyectofinal.service;

import com.cibertec.proyectofinal.model.Producto;
import com.cibertec.proyectofinal.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public List<Producto> listar() {
        return productoRepository.findAll();
    }

    @Override
    public Producto buscarPorId(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Producto no encontrado con id: " + id));
    }

    @Override
    public Producto registrar(Producto producto) {
        producto.setId(null);
        return productoRepository.save(producto);
    }

    @Override
    public Producto actualizar(Long id, Producto producto) {
        Producto actual = buscarPorId(id);
        actual.setNombre(producto.getNombre());
        actual.setDescripcion(producto.getDescripcion());
        actual.setPrecio(producto.getPrecio());
        actual.setStock(producto.getStock());
        actual.setEstado(producto.getEstado());
        return productoRepository.save(actual);
    }

    @Override
    public void eliminar(Long id) {
        Producto actual = buscarPorId(id);
        productoRepository.delete(actual);
    }
}
