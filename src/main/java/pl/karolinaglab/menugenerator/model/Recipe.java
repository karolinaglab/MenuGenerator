package pl.karolinaglab.menugenerator.model;

import pl.karolinaglab.menugenerator.enumTypes.RecipeType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String recipeName;
    private String description;
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private RecipeType recipeType;


    @ManyToMany(mappedBy = "recipes")
    private Set<Menu> menus = new HashSet<>();

    @OneToMany(mappedBy = "recipe")
    private Set<Ingredient_info> ingredients = new HashSet<>();

    public Recipe() {
    }

    public Recipe(String recipeName, String description, RecipeType recipeType) {
        this.recipeName = recipeName;
        this.description = description;
        this.recipeType = recipeType;
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

    public Set<Menu> getMenus() {
        return menus;
    }

    public void setMenus(Set<Menu> menus) {
        this.menus = menus;
    }

    public Set<Ingredient_info> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient_info> ingredients) {
        this.ingredients = ingredients;
    }
}
