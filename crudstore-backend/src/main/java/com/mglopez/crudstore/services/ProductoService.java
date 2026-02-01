package com.mglopez.crudstore.services;

import com.mglopez.crudstore.dtos.producto.ProductoCreateDTO;
import com.mglopez.crudstore.dtos.producto.ProductoResponseDTO;

import java.util.List;

public interface ProductoService {


    ProductoResponseDTO crearProducto(ProductoCreateDTO dto);

    ProductoResponseDTO obtenerPorId(Long id);

    List<ProductoResponseDTO> listarProductosActivos();

    List<ProductoResponseDTO> listarProductosPorCategoria(Long categoriaId);

    ProductoResponseDTO actualizarProducto(Long id, ProductoCreateDTO dto);

    void eliminarProducto(Long id);

}
