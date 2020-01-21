package pl.karolinaglab.menugenerator.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties("menu")
public class RecipeInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RECIPE_INFO_ID")
    private int id;

    private double numberOfPortionsForUser;
    private String recipeDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "recipeId")
    private Recipe recipe;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "menuId")
    private Menu menu;

    public RecipeInfo() {
    }

    public RecipeInfo(double numberOfPortions, Recipe recipe, Menu menu, String date) {
        this.numberOfPortionsForUser = numberOfPortions;
        this.recipe = recipe;
        this.menu = menu;
        this.recipeDate = date;
    }

    public int getId() {
        return id;
    }

    public double getNumberOfPortions() {
        return numberOfPortionsForUser;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public Menu getMenu() {
        return menu;
    }

    public double getNumberOfPortionsForUser() {
        return numberOfPortionsForUser;
    }

    public String getRecipeDate() {
        return recipeDate;
    }
}
