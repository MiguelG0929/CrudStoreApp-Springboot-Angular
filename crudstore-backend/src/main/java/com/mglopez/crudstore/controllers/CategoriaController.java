package com.mglopez.crudstore.controllers;

import com.mglopez.crudstore.dtos.categoria.CategoriaCreateDTO;
import com.mglopez.crudstore.dtos.categoria.CategoriaResponseDTO;
import com.mglopez.crudstore.services.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }


    @PostMapping //Crear Categorias
    public ResponseEntity<CategoriaResponseDTO> crearCategoria(
            @Valid @RequestBody CategoriaCreateDTO dto) {

        CategoriaResponseDTO categoriaCreada = categoriaService.crearCategoria(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaCreada);
    }

    @GetMapping //Listar Categorias Activas
    public ResponseEntity<List<CategoriaResponseDTO>> listarCategoriasActivas() {
        return ResponseEntity.ok(categoriaService.listarCategoriasActivas());
    }

    //Obtener Categoria por ID
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaService.obtenerPorId(id));
    }

    //ActualizarCategoria
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> actualizarCategoria(
            @PathVariable Long id,
            @Valid @RequestBody CategoriaCreateDTO dto) {

        return ResponseEntity.ok(categoriaService.actualizarCategoria(id, dto));
    }

    @DeleteMapping("/{id}") //Eliminar Categorias
    public ResponseEntity<Void> eliminarCategoria(@PathVariable Long id) {
        categoriaService.eliminarCategoria(id);
        return ResponseEntity.noContent().build(); // 204
    }

}
