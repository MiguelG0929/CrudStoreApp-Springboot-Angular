package com.mglopez.crudstore.services.impl;

import com.mglopez.crudstore.dtos.categoria.CategoriaCreateDTO;
import com.mglopez.crudstore.dtos.categoria.CategoriaResponseDTO;
import com.mglopez.crudstore.models.CategoriaEntity;
import com.mglopez.crudstore.repositories.CategoriaRepository;
import com.mglopez.crudstore.services.CategoriaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    // Inyección por constructor (mejor práctica que @Autowired en el atributo)
    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }


    //Crear Categorias
    @Override
    public CategoriaResponseDTO crearCategoria(CategoriaCreateDTO dto)
    {
        //validar si ya existe la categoría
        if(categoriaRepository.existsByNombre(dto.nombre()))
        {
            throw new IllegalArgumentException("La categoria ya existe:"+ dto.nombre());
        }

        CategoriaEntity categoria = CategoriaEntity.builder()
                .nombre(dto.nombre())
                .descripcion(dto.descripcion())
                .activa(true) //por defecto al momento de la creacion
                .build();

        categoriaRepository.save(categoria);

        return mapToResponseDTO(categoria);


    }

    //Listar categorias Activas
    @Override
    public List<CategoriaResponseDTO> listarCategoriasActivas()
    {
        return categoriaRepository.findByActivaTrue()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }


    //Obtener categoria por ID
    @Override
    public CategoriaResponseDTO obtenerPorId(Long id)
    {
        CategoriaEntity categoria = categoriaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Categoria no encontrada con ID" + id));
        return mapToResponseDTO(categoria);
    }


    //Metodo para actualizar categoria
    @Override
    public CategoriaResponseDTO actualizarCategoria(Long id, CategoriaCreateDTO dto)
    {
        CategoriaEntity categoria = categoriaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Categoria no encontrada con ID" + id));

        // Validación: si cambia el nombre, verificar que no exista otra categoría con ese nombre
        if (!categoria.getNombre().equals(dto.nombre()) && categoriaRepository.existsByNombre(dto.nombre())) {
            throw new IllegalArgumentException("Otra categoría ya tiene el nombre: " + dto.nombre());
        }

        categoria.setNombre(dto.nombre());
        categoria.setDescripcion(dto.descripcion());

        categoria = categoriaRepository.save(categoria);

        return mapToResponseDTO(categoria);

    }

    //Metodo para eliminar categoria
    @Override
    public void eliminarCategoria(Long id) {
        CategoriaEntity categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada con ID: " + id));

        // Desactivación lógica
        categoria.setActiva(false);
        categoriaRepository.save(categoria);
    }

    //Metodo Auxiliar para mapear Entity -> DTO
    private CategoriaResponseDTO mapToResponseDTO(CategoriaEntity categoria)
    {
        return new CategoriaResponseDTO(
                categoria.getId(),
                categoria.getNombre(),
                categoria.getDescripcion(),
                categoria.getActiva(),
                categoria.getFechaCreacion()
        );

    }
}
