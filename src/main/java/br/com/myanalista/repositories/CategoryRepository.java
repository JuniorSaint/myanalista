package br.com.myanalista.repositories;

import br.com.myanalista.models.entities.Products;
import br.com.myanalista.models.entities.User;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import br.com.myanalista.models.entities.Categories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CategoryRepository extends JpaRepository<Categories, Long> {

    @Query(value = "select c from Categories c where c.name = :name")
    Optional<Categories> findByCategoryName(@Param(value = "name") String name);

    Page<Categories> findCategoryByNameContains(String name, Pageable pageable);
    Page<Categories> findAll(Example example, Pageable pageable);

    @Query("select c from Categories c where lower(c.name) like lower(concat('%', :search, '%')) ")
    Page<Categories> findByName(@Param("search") String search, Pageable pageable);

}
