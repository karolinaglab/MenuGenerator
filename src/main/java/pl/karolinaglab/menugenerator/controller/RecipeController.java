package pl.karolinaglab.menugenerator.controller;

import org.springframework.web.bind.annotation.*;
import pl.karolinaglab.menugenerator.enumTypes.RecipeType;
import pl.karolinaglab.menugenerator.model.Recipe;
import pl.karolinaglab.menugenerator.data.RecipeDTO;
import pl.karolinaglab.menugenerator.service.RecipeService;

import java.util.List;
import java.util.Map;

@RestController
public class RecipeController {

    final private RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }


    @GetMapping("/recipe/{id}")
    public Recipe getRecipe(@PathVariable int id) throws Exception {
        return recipeService.getRecipe(id);
    }


    @PostMapping(path = "/recipe",consumes="application/json",produces="application/json")
    public Recipe addRecipee(@RequestBody RecipeDTO recipeDTO) throws Exception{

        return recipeService.addRecipe(recipeDTO);

    }

    @GetMapping("/recipes")
    public List<Recipe> geRecipes() {
        return recipeService.glutenFreeRecipes(RecipeType.BREAKFAST);
    }
}
