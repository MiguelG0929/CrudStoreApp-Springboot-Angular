package com.mglopez.crudstore.config;

import com.mglopez.crudstore.dtos.categoria.CategoriaCreateDTO;
import com.mglopez.crudstore.dtos.producto.ProductoCreateDTO;
import com.mglopez.crudstore.services.CategoriaService;
import com.mglopez.crudstore.services.ProductoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(
            CategoriaService categoriaService,
            ProductoService productoService
    ) {
        return args -> {

            var cat1 = categoriaService.crearCategoria(
                    new CategoriaCreateDTO("Electrónica", "Dispositivos electrónicos"));

            var cat2 = categoriaService.crearCategoria(
                    new CategoriaCreateDTO("Hogar", "Productos para el hogar"));

            var cat3 = categoriaService.crearCategoria(
                    new CategoriaCreateDTO("Ropa", "Ropa y accesorios"));

            productoService.crearProducto(
                    new ProductoCreateDTO(
                            "Laptop Gamer",
                            "Laptop con RTX 4060",
                            new BigDecimal("1299.99"),
                            cat1.id()
                    )
            );

            productoService.crearProducto(
                    new ProductoCreateDTO(
                            "Aspiradora",
                            "Aspiradora inteligente",
                            new BigDecimal("299.99"),
                            cat2.id()
                    )
            );

            productoService.crearProducto(
                    new ProductoCreateDTO(
                            "Chaqueta",
                            "Chaqueta impermeable",
                            new BigDecimal("89.99"),
                            cat3.id()
                    )
            );
        };
    }
}