package pl.karolinaglab.menugenerator.model;

import org.hibernate.annotations.Type;
import pl.karolinaglab.menugenerator.enumTypes.RecipeType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String recipeName;
    @Type(type="text")
    private String description;
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private RecipeType recipeType;
    private double totalCalories;
    private double caloriesForPortion;
    private int numberOfPortions;
    private boolean glutenFree;
    private boolean lactoseFree;
    private boolean vegetarian;


  /*  @ManyToMany(mappedBy = "recipes")
    private Set<Menu> menus = new HashSet<>(); */

    @OneToMany(mappedBy = "recipe")
   // private Set<Ingredient_info> ingredients = new HashSet<>();
    private Set<Ingredient_info> ingredient_infos = new HashSet<>();

    @OneToMany(mappedBy = "recipe")
    private Set<RecipeInfo> recipeInfos = new HashSet<>();

    public Recipe() {
    }

    public Recipe(String recipeName, String description, RecipeType recipeType, int numberOfPortions, boolean glutenFree, boolean lactoseFree, boolean vegetarian) {
        this.recipeName = recipeName;
        this.description = description;
        this.recipeType = recipeType;
        this.numberOfPortions = numberOfPortions;
        this.glutenFree = glutenFree;
        this.lactoseFree = lactoseFree;
        this.vegetarian = vegetarian;

    }

    public void setTotalCalories(List<Ingredient_info> ingredientsList) {
        double caloriesCounter = 0;
        this.ingredient_infos =  new HashSet<>(ingredientsList);
        for (Ingredient_info ingredient : ingredient_infos) {
            caloriesCounter += ingredient.getIngredientCalories();
        }

        this.totalCalories = caloriesCounter;
        this.caloriesForPortion = this.totalCalories / this.numberOfPortions;
    }

    public int getId() {
        return id;
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

  /*  public Set<Menu> getMenus() {
        return menus;
    }

    public void setMenus(Set<Menu> menus) {
        this.menus = menus;
    }*/

    public Set<Ingredient_info> getIngredient_infos() {
        return ingredient_infos;
    }

    public void setIngredient_infos(Set<Ingredient_info> ingredient_infos) {
        this.ingredient_infos = ingredient_infos;
    }

    public double getTotalCalories() {
        return totalCalories;
    }

    public double getCaloriesForPortion() {
        return caloriesForPortion;
    }

    public int getNumberOfPortions() {
        return numberOfPortions;
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

    public Set<RecipeInfo> getRecipeInfos() {
        return recipeInfos;
    }
}
