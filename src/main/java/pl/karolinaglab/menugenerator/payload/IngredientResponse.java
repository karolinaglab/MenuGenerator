package pl.karolinaglab.menugenerator.payload;

import pl.karolinaglab.menugenerator.enumTypes.AmountType;
import pl.karolinaglab.menugenerator.model.Ingredient;

public class IngredientResponse {

    private int id;
    private String name;
    private String calories;

    public IngredientResponse(Ingredient ingredient) {

        this.id = ingredient.getId();
        this.name = ingredient.getIngrName();
        if (ingredient.getAmountType().equals(AmountType.GRAMS) || ingredient.getAmountType().equals(AmountType.LITERS)) {
            this.calories = ingredient.getCalories() * 100 + " kcal na 100" + ingredient.getAmountType().toString();
        } else {
            this.calories = ingredient.getCalories() + "kcal na 1" + ingredient.getAmountType().toString();
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCalories() {
        return calories;
    }
}
