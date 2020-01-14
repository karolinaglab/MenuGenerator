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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "recipeId")
    private Recipe recipe;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "menuId")
    private Menu menu;

    public RecipeInfo() {
    }

    public RecipeInfo(double numberOfPortions, Recipe recipe, Menu menu) {
        this.numberOfPortionsForUser = numberOfPortions;
        this.recipe = recipe;
        this.menu = menu;
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
}
