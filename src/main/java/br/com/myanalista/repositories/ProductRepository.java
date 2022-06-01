package br.com.myanalista.repositories;

import org.springframework.stereotype.Repository;

import br.com.myanalista.models.entities.ProductsEntity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface ProductRepository extends JpaRepository<ProductsEntity, Long>{
  Optional<ProductsEntity> findById(Long id);

  @Query(value = "select u from ProductsEntity u where u.sku = :sku")
  Optional<ProductsEntity> findBySku(@Param(value = "sku") String sku);
}
