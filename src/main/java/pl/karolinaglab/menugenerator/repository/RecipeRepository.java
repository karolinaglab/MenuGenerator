package pl.karolinaglab.menugenerator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.karolinaglab.menugenerator.enumTypes.RecipeType;
import pl.karolinaglab.menugenerator.model.Recipe;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {

    List<Recipe> findByRecipeType(RecipeType recipeType);

    List<Recipe> findAllByRecipeTypeAndGlutenFreeTrue(RecipeType recipeType);

    List<Recipe> findAllByRecipeTypeAndLactoseFreeTrue(RecipeType recipeType);

    List<Recipe> findAllByRecipeTypeAndVegetarianTrue(RecipeType recipeType);
}
