package com.mglopez.crudstore.dtos.categoria;

import java.time.LocalDateTime;

//DTO DE SALIDA
public record CategoriaResponseDTO(
        Long id,
        String nombre,
        String descripcion,
        Boolean activa,
        LocalDateTime fechaCreacion
) {}
