package br.com.myanalista.repositories;

import org.springframework.stereotype.Repository;
import br.com.myanalista.models.entities.Categories;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface CategoryRepository extends JpaRepository<Categories, Long> {
  Optional<Categories> findById(Long id);
}
