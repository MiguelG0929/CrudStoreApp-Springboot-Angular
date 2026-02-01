package com.mglopez.crudstore.services.impl;

import com.mglopez.crudstore.dtos.producto.ProductoCreateDTO;
import com.mglopez.crudstore.dtos.producto.ProductoResponseDTO;
import com.mglopez.crudstore.models.CategoriaEntity;
import com.mglopez.crudstore.models.ProductoEntity;
import com.mglopez.crudstore.repositories.CategoriaRepository;
import com.mglopez.crudstore.repositories.ProductoRepository;
import com.mglopez.crudstore.services.ProductoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductoServiceImpl implements ProductoService
{

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;


    //Inyeccion por constructores practica mejor y moderna que @Autowired
    public ProductoServiceImpl(ProductoRepository productoRepository, CategoriaRepository categoriaRepository) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
    }


    @Override
    //Metodo para crear producto
    public ProductoResponseDTO crearProducto(ProductoCreateDTO dto)
    {
        CategoriaEntity categoria = categoriaRepository.findById(dto.categoriaId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Categoría no encontrada con ID: " + dto.categoriaId()));

        ProductoEntity producto = ProductoEntity.builder()
                .name(dto.name())
                .descripcion(dto.descripcion())
                .precio(dto.precio())
                .activo(true)
                .categoria(categoria)
                .build();

        producto = productoRepository.save(producto);

        return mapToResponseDTO(producto);
    }

    @Override
    //metodo para obtener producto por ID
    public ProductoResponseDTO obtenerPorId(Long id)
    {
        ProductoEntity producto = productoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Producto no encontrado con ID: " + id));

        return mapToResponseDTO(producto);
    }

    @Override
    //metodo para listar productos Activos
    public List<ProductoResponseDTO> listarProductosActivos() {
        return productoRepository.findByActivoTrue()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    //metodo para lista producto por categoria
    public List<ProductoResponseDTO> listarProductosPorCategoria(Long categoriaId) {
        return productoRepository.findByCategoriaIdAndActivoTrue(categoriaId)
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    //metodo para actualizar producto
    public ProductoResponseDTO actualizarProducto(Long id, ProductoCreateDTO dto) {
        ProductoEntity producto = productoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Producto no encontrado con ID: " + id));

        CategoriaEntity categoria = categoriaRepository.findById(dto.categoriaId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Categoría no encontrada con ID: " + dto.categoriaId()));

        producto.setName(dto.name());
        producto.setDescripcion(dto.descripcion());
        producto.setPrecio(dto.precio());
        producto.setCategoria(categoria);

        producto = productoRepository.save(producto);

        return mapToResponseDTO(producto);
    }

    //metodo para eliminar producto
    @Override
    public void eliminarProducto(Long id) {

        ProductoEntity producto = productoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Producto no encontrado con ID: " + id));

        // Eliminación lógica
        producto.setActivo(false);
        productoRepository.save(producto);
    }



    //mapeo de ProductoEntity a DTO
    private ProductoResponseDTO mapToResponseDTO(ProductoEntity producto) {
        return new ProductoResponseDTO(
                producto.getId(),
                producto.getName(),
                producto.getDescripcion(),
                producto.getPrecio(),
                producto.getActivo(),
                producto.getCategoria().getId(),
                producto.getCategoria().getNombre(),
                producto.getFechaCreacion()
        );
    }
}
