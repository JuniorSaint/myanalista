package br.com.myanalista.repositories;

import org.springframework.stereotype.Repository;

import br.com.myanalista.models.entities.ProductsEntity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProductRepository extends JpaRepository<ProductsEntity, Long>{
  Optional<ProductsEntity> findById(Long id);
}
