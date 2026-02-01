package com.mglopez.crudstore.dtos.categoria;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

//DTO DE ENTRADA
public record CategoriaCreateDTO (

        @NotBlank
        @Size(max = 50)
        String nombre,

        @Size(max = 150)
        String descripcion

) {}
