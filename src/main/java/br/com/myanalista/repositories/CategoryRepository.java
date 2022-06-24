package br.com.myanalista.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import br.com.myanalista.models.entities.Categories;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface CategoryRepository extends JpaRepository<Categories, Long> {
  Optional<Categories> findById(Long id);

  @Query(value = "select c from Categories c where c.name = :name")
  Optional<Categories> findCategoryByName(@Param(value = "name") String name);
}
