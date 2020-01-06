package pl.karolinaglab.menugenerator.model;

import pl.karolinaglab.menugenerator.enumTypes.RecipeType;

import javax.persistence.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RecipeDTO {
    private String recipeName;
    private String description;
    private RecipeType recipeType;
    private int numberOfPortions;
    private Map<String, Double> ingredientInfo = new HashMap<>();

    public RecipeDTO(String recipeName, String description, RecipeType recipeType, int numberOfPortions, Map<String, Double> ingredientInfo) {
        this.recipeName = recipeName;
        this.description = description;
        this.recipeType = recipeType;
        this.numberOfPortions = numberOfPortions;
        this.ingredientInfo = ingredientInfo;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RecipeType getRecipeType() {
        return recipeType;
    }

    public void setRecipeType(RecipeType recipeType) {
        this.recipeType = recipeType;
    }

    public int getNumberOfPortions() {
        return numberOfPortions;
    }

    public void setNumberOfPortions(int numberOfPortions) {
        this.numberOfPortions = numberOfPortions;
    }

    public Map<String, Double> getIngredientInfo() {
        return ingredientInfo;
    }

    public void setIngredientInfo(Map<String, Double> ingredientInfo) {
        this.ingredientInfo = ingredientInfo;
    }
}
