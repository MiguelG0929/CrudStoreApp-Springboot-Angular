package com.mglopez.crudstore.dtos.producto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

//DTO DE SALIDA

public record ProductoResponseDTO(
        Long id,
        String name,
        String descripcion,
        BigDecimal precio,
        Boolean activo,
        Long categoriaId,
        String categoriaNombre,
        LocalDateTime fechaCreacion
)
{}
