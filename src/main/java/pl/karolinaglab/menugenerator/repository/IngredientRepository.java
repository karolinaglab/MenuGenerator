package pl.karolinaglab.menugenerator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Repository;
import pl.karolinaglab.menugenerator.model.Ingredient;

import java.util.List;
import java.util.Optional;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {

   // Ingredient findByIngrName(String ingrName);
   Optional<Ingredient> findByIngrName(String ingrName);
   List<Ingredient> findByIngrNameContaining(String ingrName);

}
