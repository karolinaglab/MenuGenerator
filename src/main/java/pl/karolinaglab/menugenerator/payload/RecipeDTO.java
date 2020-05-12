package pl.karolinaglab.menugenerator.payload;

import pl.karolinaglab.menugenerator.enumTypes.RecipeType;

import java.util.*;

public class RecipeDTO {
    private String recipeName;
    private String description;
    private RecipeType recipeType;
    private int numberOfPortions;
    private List<IngredientData> ingredientData;
    private boolean glutenFree;
    private boolean lactoseFree;
    private boolean vegetarian;
    private String src;

    public RecipeDTO(String recipeName, String description, RecipeType recipeType, int numberOfPortions, Map<String, Double> ingredientInfo, List<IngredientData> ingredientData, boolean glutenFree, boolean lactoseFree, boolean vegetarian, String src) {
        this.recipeName = recipeName;
        this.description = description;
        this.recipeType = recipeType;
        this.numberOfPortions = numberOfPortions;
        this.ingredientData = ingredientData;
        this.glutenFree = glutenFree;
        this.lactoseFree = lactoseFree;
        this.vegetarian = vegetarian;
        this.src = src;
    }

    public List<IngredientData> getIngredientData() {
        return ingredientData;
    }

    public void setIngredientData(List<IngredientData> ingredientData) {
        this.ingredientData = ingredientData;
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

    public boolean isGlutenFree() {
        return glutenFree;
    }

    public boolean isLactoseFree() {
        return lactoseFree;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public String getSrc() {
        return src;
    }
}
