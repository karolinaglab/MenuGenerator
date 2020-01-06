package pl.karolinaglab.menugenerator.creators;

import pl.karolinaglab.menugenerator.enumTypes.MenuType;
import pl.karolinaglab.menugenerator.model.Menu;
import pl.karolinaglab.menugenerator.model.Recipe;
import pl.karolinaglab.menugenerator.model.RecipeInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MenuCreator {

    public static List<RecipeInfo> createMenu(List<Recipe> breakfasts, List<Recipe> secondMeals, List<Recipe> dinners, List<Recipe> suppers, double userCalories, Menu menu) {

        List<RecipeInfo> recipeInfos = new ArrayList<>();

        double breakfastsCalories = userCalories * 0.25;
        double secondMealsCalories = userCalories * 0.1;
        double dinnersCalories = userCalories * 0.4;
        double suppersCalories = userCalories * 0.25;

        int numberOfBreakfasts = breakfasts.size();
        int numberOfSecondMeals = secondMeals.size();
        int numberOfDinners = dinners.size();
        int numberOfSuppers = suppers.size();


        Random rand = new Random();

        if (menu.getMenuType().equals(MenuType.DAILY_MENU)) {
            int breakfastNumber = rand.nextInt(numberOfBreakfasts);
            int secondMealNumber = rand.nextInt(numberOfSecondMeals);
            int dinnerNumber = rand.nextInt(numberOfDinners);
            int supperNumber = rand.nextInt(numberOfSuppers);

            Recipe breakfastRecipe = breakfasts.get(breakfastNumber);
            Recipe secondMealRecipe = secondMeals.get(secondMealNumber);
            Recipe dinnerRecipe = dinners.get(dinnerNumber);
            Recipe supperRecipe = suppers.get(supperNumber);


        }
        return recipeInfos;
    }

}
