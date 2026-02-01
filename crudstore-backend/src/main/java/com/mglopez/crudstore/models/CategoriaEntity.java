package com.mglopez.crudstore.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity

@Table(name = "categorias")
public class CategoriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(length = 50, nullable = false, unique = true)
    private String nombre;

    @Column(length = 150)
    private String descripcion;

    @NotNull
    private Boolean activa;

    @CreationTimestamp
    @Column(name = "fecha_creacion", updatable = false)
    private LocalDateTime fechaCreacion;

    // Relación bidireccional
    @OneToMany(mappedBy = "categoria", fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude   //evitar stackoverflow
    private Set<ProductoEntity> productos = new HashSet<>();

}
