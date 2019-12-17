package pl.karolinaglab.menugenerator.controller;


import org.springframework.web.bind.annotation.RestController;
import pl.karolinaglab.menugenerator.repository.IngredientRepository;
import pl.karolinaglab.menugenerator.repository.RecipeRepository;

@RestController
public class RecipeController {

    private IngredientRepository ingredientRepository;
    private RecipeRepository recipeRepository;
}
