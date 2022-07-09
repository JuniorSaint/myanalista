package br.com.myanalista.repositories;

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

    Page<Categories> findAllByName(String name, Pageable pageable);
    @Query(value = "select c from Categories c where c.name = :name")
    Optional<Categories> findByCategoryName(@Param(value = "name") String name);
}
