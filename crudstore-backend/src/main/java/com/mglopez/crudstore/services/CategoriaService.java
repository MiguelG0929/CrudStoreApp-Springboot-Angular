package com.mglopez.crudstore.services;

import com.mglopez.crudstore.dtos.categoria.CategoriaCreateDTO;
import com.mglopez.crudstore.dtos.categoria.CategoriaResponseDTO;

import java.util.List;

public interface CategoriaService {

    // Crear nueva categoría
    CategoriaResponseDTO crearCategoria(CategoriaCreateDTO categoriaCreateDTO);

    // Listar categorías activas
    List<CategoriaResponseDTO> listarCategoriasActivas();

    // Obtener categoría por ID
    CategoriaResponseDTO obtenerPorId(Long id);

    // Actualizar categoría
    CategoriaResponseDTO actualizarCategoria(Long id, CategoriaCreateDTO categoriaCreateDTO);

    // Eliminar categoría (desactivación lógica)
    void eliminarCategoria(Long id);





}
