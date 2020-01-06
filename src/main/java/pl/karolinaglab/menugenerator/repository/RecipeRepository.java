package pl.karolinaglab.menugenerator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.karolinaglab.menugenerator.model.Recipe;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
}
