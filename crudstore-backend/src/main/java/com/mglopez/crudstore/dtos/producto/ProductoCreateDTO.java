package com.mglopez.crudstore.dtos.producto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

//DTO DE ENTRADA
public record ProductoCreateDTO(
        @NotBlank
        String name,

        @NotBlank
        String descripcion,

        @NotNull
        @Positive
        BigDecimal precio,

        @NotNull
        Long categoriaId
) {}
