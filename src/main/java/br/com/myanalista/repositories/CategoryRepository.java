package br.com.myanalista.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import br.com.myanalista.models.entities.Categories;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface CategoryRepository extends JpaRepository<Categories, Long> {
  @Query(value = "select c from Categories c where c.id = :id ")
  Optional<Categories> findByIdSecundary(@Param(value = "id") Long id);


}
