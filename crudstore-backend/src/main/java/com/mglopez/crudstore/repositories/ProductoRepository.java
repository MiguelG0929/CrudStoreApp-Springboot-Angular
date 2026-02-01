package com.mglopez.crudstore.repositories;

import com.mglopez.crudstore.models.CategoriaEntity;
import com.mglopez.crudstore.models.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<ProductoEntity, Long> {

    Optional<ProductoEntity> findByName(String name);

    List<ProductoEntity> findByActivoTrue();

    List<ProductoEntity> findByCategoriaId(Long categoriaId);

    List<ProductoEntity> findByCategoriaIdAndActivoTrue(Long categoriaId);


}
