package pl.karolinaglab.menugenerator.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="Ingredient_info")
@JsonIgnoreProperties("recipe")
//@IdClass(Ingredient_infoPK.class)
public class Ingredient_info{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "INGREDIENT_INFO_ID")
    private int id;

    private double amount;
    private double ingredientCalories;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "recipeId")
    private Recipe recipe;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ingrId")
    private Ingredient ingredient;

    public Ingredient_info() {
    }

    public Ingredient_info(double amount, Ingredient ingredient, Recipe recipe) {
        this.amount = amount;
        this.ingredient = ingredient;
        this.recipe = recipe;
        this.ingredientCalories = this.amount * ingredient.getCalories();
    }

   /* public int getRecipeId() {
        return recipeId;
    }

    public int getIngrId() {
        return ingrId;
    }*/

    public double getAmount() {
        return amount;
    }

    public double getIngredientCalories() {
        return ingredientCalories;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }
}
