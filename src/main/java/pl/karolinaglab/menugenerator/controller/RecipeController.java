package pl.karolinaglab.menugenerator.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import pl.karolinaglab.menugenerator.enumTypes.FoodPreferences;
import pl.karolinaglab.menugenerator.enumTypes.RecipeType;
import pl.karolinaglab.menugenerator.model.Recipe;
import pl.karolinaglab.menugenerator.payload.RecipeDTO;
import pl.karolinaglab.menugenerator.service.RecipeService;

import java.util.List;
import java.util.Map;


@RestController
public class RecipeController {

    final private RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/recipe/{id}")
    public Recipe getRecipe(@PathVariable int id) throws Exception {
        return recipeService.getRecipe(id);
    }


    @Secured("ROLE_ADMIN")
    @PostMapping(path = "/recipe",consumes="application/json",produces="application/json")
    public Recipe addRecipe(@RequestBody RecipeDTO recipeDTO) throws Exception{

        return recipeService.addRecipe(recipeDTO);

    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/recipes")
    public List<Recipe> getRecipes() {
        return recipeService.findAllRecipes();
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/chosenrecipes")
    public List<Recipe> getRecipes(@RequestParam String type, @RequestParam String preferences) {
        RecipeType recipeType = RecipeType.valueOf(type);
        FoodPreferences foodPreferences = FoodPreferences.valueOf(preferences);
        return recipeService.findRecipesWithPreferences(recipeType, foodPreferences);
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/recipe/{id}")
    public Map<String, Boolean> deleteRecipe(@PathVariable String id) throws Exception {
        int recipeId = Integer.parseInt(id);
        return recipeService.deleteRecipe(recipeId);
    }
}
