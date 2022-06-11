package br.com.myanalista.repositories;

import org.springframework.stereotype.Repository;

import br.com.myanalista.models.entities.Products;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface ProductRepository extends JpaRepository<Products, String>{
  Optional<Products> findByCode(String code);

  @Query(value = "select u from Products u where u.sku = :sku")
  Optional<Products> findBySku(@Param(value = "sku") String sku);


}
