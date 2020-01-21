package pl.karolinaglab.menugenerator.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import pl.karolinaglab.menugenerator.enumTypes.RecipeType;
import pl.karolinaglab.menugenerator.model.Recipe;
import pl.karolinaglab.menugenerator.payload.RecipeDTO;
import pl.karolinaglab.menugenerator.security.CurrentUser;
import pl.karolinaglab.menugenerator.security.UserPrincipal;
import pl.karolinaglab.menugenerator.service.RecipeService;

import java.util.List;


@RestController
public class RecipeController {

    final private RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/recipe/{id}")
    public Recipe getRecipe(@PathVariable int id, @CurrentUser UserPrincipal currentUser) throws Exception {
        return recipeService.getRecipe(currentUser, id);
    }


    @Secured("ROLE_ADMIN")
    @PostMapping(path = "/recipe",consumes="application/json",produces="application/json")
    public Recipe addRecipe(@RequestBody RecipeDTO recipeDTO) throws Exception{

        return recipeService.addRecipe(recipeDTO);

    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/recipes")
    public List<Recipe> geRecipes() {
        return recipeService.glutenFreeRecipes(RecipeType.BREAKFAST);
    }
}
