package br.com.myanalista.repositories;

import br.com.myanalista.models.entities.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;

import br.com.myanalista.models.entities.Products;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface ProductRepository extends JpaRepository<Products, Long> {
    @Query(value = "select u from Products u where u.sku = :sku")
    Optional<Products> findByCodeSku(@Param(value = "sku") Integer sku);

    Page<Products> findAll(Pageable pageable);

    @EntityGraph(attributePaths = "categories")
    @Query("select p from Products p  LEFT JOIN p.categories c where lower(p.productDescription) like lower(concat('%', :search, '%')) " +
            "or CAST(p.sku as text) like lower(concat('%', :search, '%'))" +
            "or CAST(p.active as text ) like lower(concat('%', :search , '%'))" +
            "or lower(p.productDescription) like lower(concat('%', :search, '%'))" +
            "or lower(c.name) like lower(concat('%', :search , '%'))")
    Page<Products> findByActiveOrSkuOrProductDescription(@Param("search") String search, Pageable pageable);

}
