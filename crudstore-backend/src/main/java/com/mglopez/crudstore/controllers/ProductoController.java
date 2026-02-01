package com.mglopez.crudstore.controllers;

import com.mglopez.crudstore.dtos.producto.ProductoCreateDTO;
import com.mglopez.crudstore.dtos.producto.ProductoResponseDTO;
import com.mglopez.crudstore.services.ProductoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;


    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping   //Crear Producto
    public ResponseEntity<ProductoResponseDTO> crearProducto(
            @Valid @RequestBody ProductoCreateDTO dto) {

        ProductoResponseDTO productoCreado = productoService.crearProducto(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoCreado);
    }

    @GetMapping //Listar productos Activos
    public ResponseEntity<List<ProductoResponseDTO>> listarProductosActivos() {
        return ResponseEntity.ok(productoService.listarProductosActivos());
    }

    @GetMapping("/{id}")  //Obtener productos por ID
    public ResponseEntity<ProductoResponseDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.obtenerPorId(id));
    }


    @GetMapping("/categoria/{categoriaId}") //Obtener productos por categoria
    public ResponseEntity<List<ProductoResponseDTO>> listarPorCategoria(
            @PathVariable Long categoriaId) {

        return ResponseEntity.ok(
                productoService.listarProductosPorCategoria(categoriaId)
        );
    }

    @PutMapping("/{id}") //Actualizar producto
    public ResponseEntity<ProductoResponseDTO> actualizarProducto(
            @PathVariable Long id,
            @Valid @RequestBody ProductoCreateDTO dto) {

        return ResponseEntity.ok(
                productoService.actualizarProducto(id, dto)
        );
    }

    @DeleteMapping("/{id}") //Eliminar Producto
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build(); // 204
    }


}
