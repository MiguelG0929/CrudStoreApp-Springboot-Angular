package com.mglopez.crudstore.repositories;

import com.mglopez.crudstore.models.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Long> {

    boolean existsByNombre(String nombre);

    Optional<CategoriaEntity> findByNombre(String nombre);

    List<CategoriaEntity> findByActivaTrue();


}
