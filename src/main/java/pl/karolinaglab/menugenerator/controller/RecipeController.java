package pl.karolinaglab.menugenerator.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.karolinaglab.menugenerator.enumTypes.RecipeType;
import pl.karolinaglab.menugenerator.exceptions.ResourceNotFoundException;
import pl.karolinaglab.menugenerator.model.Ingredient;
import pl.karolinaglab.menugenerator.model.Ingredient_info;
import pl.karolinaglab.menugenerator.model.Recipe;
import pl.karolinaglab.menugenerator.model.RecipeDTO;
import pl.karolinaglab.menugenerator.repository.IngredientRepository;
import pl.karolinaglab.menugenerator.repository.Ingredient_infoRepository;
import pl.karolinaglab.menugenerator.repository.RecipeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class RecipeController {

    private IngredientRepository ingredientRepository;
    private RecipeRepository recipeRepository;
    private Ingredient_infoRepository ingredient_infoRepository;


    public RecipeController(IngredientRepository ingredientRepository, RecipeRepository recipeRepository, Ingredient_infoRepository ingredient_infoRepository) {
        this.ingredientRepository = ingredientRepository;
        this.recipeRepository = recipeRepository;
        this.ingredient_infoRepository = ingredient_infoRepository;
    }

    @PostMapping("/recipe")
    public Recipe addRecipe(@RequestBody Map<String,String> body) throws Exception{

        String recipeName = body.get("recipeName");
        String description = body.get("description");
        String recipeTypeString = body.get("recipeType");
        RecipeType recipeType = RecipeType.valueOf(recipeTypeString);
        String numberOfPortionsString = body.get("numberOfPortions");
        int numberOfPortions = Integer.parseInt(numberOfPortionsString);

        String allIngredients = body.get("ingredients");
        String allAmounts = body.get("amounts");

        String[] ingredientsNames = allIngredients.split(",");
        String[] ingredientsAmountsString = allAmounts.split(",");

        List<Double> ingredientsAmounts = new ArrayList<>();
        for (String s : ingredientsAmountsString) {
            ingredientsAmounts.add(Double.parseDouble(s));
        }

        List<Ingredient> ingredients = new ArrayList<>();

        for (String name : ingredientsNames) {
            Optional<Ingredient> ingredientToAdd = ingredientRepository.findByIngrName(name);
            if (ingredientToAdd.isPresent()) {
                ingredients.add(ingredientToAdd.get());
            } else {
                throw new ResourceNotFoundException("Ingredient not found on : " + name);
            }
        }

        Recipe recipeToAdd = new Recipe(recipeName, description, recipeType, numberOfPortions);
        recipeRepository.save(recipeToAdd);
        List<Ingredient_info> ingredient_infos = new ArrayList<>();
        for (int i = 0; i < ingredients.size(); i++) {
            Ingredient_info ingredient_infoToAdd = new Ingredient_info(ingredientsAmounts.get(i), ingredients.get(i), recipeToAdd);
            ingredient_infoRepository.save(ingredient_infoToAdd);
            ingredient_infos.add(ingredient_infoToAdd);
        }

        recipeToAdd.setTotalCalories(ingredient_infos);

        //Recipe recipeToAdd = new Recipe(recipeName, description, recipeType, numberOfPortions, ingredient_infos);

        return recipeRepository.save(recipeToAdd);
    }

    @PostMapping("/recipe2")
    public Recipe addRecipe2(@RequestBody RecipeDTO recipeDTO) throws Exception {
        System.out.println(recipeDTO.getRecipeName());

        return new Recipe();
    }
}
