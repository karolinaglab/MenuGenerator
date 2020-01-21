package pl.karolinaglab.menugenerator.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.karolinaglab.menugenerator.payload.IngredientData;
import pl.karolinaglab.menugenerator.payload.RecipeDTO;
import pl.karolinaglab.menugenerator.enumTypes.RecipeType;
import pl.karolinaglab.menugenerator.exceptions.ResourceNotFoundException;
import pl.karolinaglab.menugenerator.model.Ingredient;
import pl.karolinaglab.menugenerator.model.Ingredient_info;
import pl.karolinaglab.menugenerator.model.Recipe;
import pl.karolinaglab.menugenerator.repository.IngredientRepository;
import pl.karolinaglab.menugenerator.repository.Ingredient_infoRepository;
import pl.karolinaglab.menugenerator.repository.RecipeRepository;
import pl.karolinaglab.menugenerator.security.UserPrincipal;

import java.util.*;

@Service
public class RecipeService {

    final private IngredientRepository ingredientRepository;
    final private RecipeRepository recipeRepository;
    final private Ingredient_infoRepository ingredient_infoRepository;

    private static final Logger logger = LoggerFactory.getLogger(RecipeService.class);

    public RecipeService(IngredientRepository ingredientRepository, RecipeRepository recipeRepository, Ingredient_infoRepository ingredient_infoRepository) {
        this.ingredientRepository = ingredientRepository;
        this.recipeRepository = recipeRepository;
        this.ingredient_infoRepository = ingredient_infoRepository;
    }


    public Recipe addRecipe(RecipeDTO recipeDTO) throws Exception {
        String recipeName = recipeDTO.getRecipeName();
        RecipeType recipeType = recipeDTO.getRecipeType();
        int numberOfPortions = recipeDTO.getNumberOfPortions();
        String description = recipeDTO.getDescription();
        List<IngredientData> ingredientData = recipeDTO.getIngredientData();
        boolean glutenFree = recipeDTO.isGlutenFree();
        boolean lactoseFree = recipeDTO.isLactoseFree();
        boolean vegetarian = recipeDTO.isVegetarian();

        Recipe recipeToAdd = new Recipe(recipeName, description, recipeType, numberOfPortions, glutenFree, lactoseFree, vegetarian);
        recipeRepository.save(recipeToAdd);

        List<Ingredient_info> ingredient_infos = new ArrayList<>();
        for (IngredientData ingredientDatum : ingredientData) {

            String ingredientName = ingredientDatum.getName();
            double ingredientAmount = ingredientDatum.getAmount();
            Optional<Ingredient> ingredientToAdd = ingredientRepository.findByIngrName(ingredientName);
            if (ingredientToAdd.isPresent()) {
                Ingredient_info ingredient_infoToAdd = new Ingredient_info(ingredientAmount, ingredientToAdd.get(), recipeToAdd);
                ingredient_infoRepository.save(ingredient_infoToAdd);
                ingredient_infos.add(ingredient_infoToAdd);
            } else {
                throw new ResourceNotFoundException("Ingredient not found on : " + ingredientName);
            }
        }
        recipeToAdd.setTotalCalories(ingredient_infos);
        return recipeRepository.save(recipeToAdd);
    }

    public Recipe getRecipe(UserPrincipal currentUser, int id) throws Exception{
        Optional<Recipe> recipe = recipeRepository.findById(id);
        if (recipe.isPresent()) {
            return recipe.get();
        } else {
            throw new ResourceNotFoundException("Recipe not found on : " + id);
        }
    }

    public List<Recipe> glutenFreeRecipes(RecipeType recipeType) {
        return recipeRepository.findAllByRecipeTypeAndGlutenFreeTrue(recipeType);
    }
}
