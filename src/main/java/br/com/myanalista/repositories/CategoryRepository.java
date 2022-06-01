package br.com.myanalista.repositories;

import org.springframework.stereotype.Repository;
import br.com.myanalista.models.entities.CategoriesEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface CategoryRepository extends JpaRepository<CategoriesEntity, Long> {
  Optional<CategoriesEntity> findById(Long id);

}
