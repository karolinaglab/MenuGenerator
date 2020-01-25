package pl.karolinaglab.menugenerator.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.karolinaglab.menugenerator.exceptions.ResourceAlreadyExistException;
import pl.karolinaglab.menugenerator.payload.IngredientData;
import pl.karolinaglab.menugenerator.payload.RecipeDTO;
import pl.karolinaglab.menugenerator.enumTypes.RecipeType;
import pl.karolinaglab.menugenerator.exceptions.ResourceNotFoundException;
import pl.karolinaglab.menugenerator.model.Ingredient;
import pl.karolinaglab.menugenerator.model.IngredientInfo;
import pl.karolinaglab.menugenerator.model.Recipe;
import pl.karolinaglab.menugenerator.repository.*;
import pl.karolinaglab.menugenerator.security.UserPrincipal;

import java.util.*;

@Service
public class RecipeService {

    final private IngredientRepository ingredientRepository;
    final private RecipeRepository recipeRepository;
    final private IngredientInfoRepository ingredientInfoRepository;
    final private RecipeInfoRepository recipeInfoRepository;

    private static final Logger logger = LoggerFactory.getLogger(RecipeService.class);

    public RecipeService(IngredientRepository ingredientRepository, RecipeRepository recipeRepository, IngredientInfoRepository ingredientInfoRepository, RecipeInfoRepository recipeInfoRepository) {
        this.ingredientRepository = ingredientRepository;
        this.recipeRepository = recipeRepository;
        this.ingredientInfoRepository = ingredientInfoRepository;
        this.recipeInfoRepository = recipeInfoRepository;
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

        List<IngredientInfo> ingredientInfos = new ArrayList<>();
        for (IngredientData ingredientDatum : ingredientData) {

            String ingredientName = ingredientDatum.getName();
            double ingredientAmount = ingredientDatum.getAmount();
            Optional<Ingredient> ingredientToAdd = ingredientRepository.findByIngrName(ingredientName);
            if (ingredientToAdd.isPresent()) {
                IngredientInfo ingredient_infoToAdd = new IngredientInfo(ingredientAmount, ingredientToAdd.get(), recipeToAdd);
                ingredientInfoRepository.save(ingredient_infoToAdd);
                ingredientInfos.add(ingredient_infoToAdd);
            } else {
                throw new ResourceNotFoundException("Ingredient not found on : " + ingredientName);
            }
        }
        recipeToAdd.setTotalCalories(ingredientInfos);
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

    public Map<String, Boolean> deleteRecipe(int id) throws Exception {

        Optional<Recipe> recipeToDelete = recipeRepository.findById(id);
        if (recipeToDelete.isPresent()) {
            if (recipeInfoRepository.findAllByRecipeId(id).isEmpty()) {
                List<IngredientInfo> ingredientInfos = ingredientInfoRepository.findAllByRecipeId(id);
                for (IngredientInfo ingredientInfo : ingredientInfos) {
                    ingredientInfoRepository.delete(ingredientInfo);
                }
                recipeRepository.deleteById(id);
                Map<String, Boolean> response = new HashMap<>();
                response.put("deleted", Boolean.TRUE);
                return response;
            } throw new ResourceAlreadyExistException("Recipe found in menu, You can't delete it!");
        } else {
            throw new ResourceNotFoundException("Recipe not found on : " + id);
        }
    }
}
