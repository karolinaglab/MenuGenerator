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

        double userBreakfastsCalories = userCalories * 0.25;
        double userSecondMealsCalories = userCalories * 0.1;
        double userDinnersCalories = userCalories * 0.4;
        double userSuppersCalories = userCalories * 0.25;

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

            double breakfastRecipeCalories = breakfastRecipe.getCaloriesForPortion();
            double secondMealRecipeCalories = secondMealRecipe.getCaloriesForPortion();
            double dinnerRecipeCalories = dinnerRecipe.getCaloriesForPortion();
            double supperRecipeCalories = supperRecipe.getCaloriesForPortion();

            double breakfastPortions = userBreakfastsCalories / breakfastRecipeCalories;
            double secondMealPortions = userSecondMealsCalories / secondMealRecipeCalories;
            double dinnerPortions = userDinnersCalories / dinnerRecipeCalories ;
            double supperPortions = userSuppersCalories / supperRecipeCalories;

            double bP  = (int) breakfastPortions;
            double smP = (int) secondMealPortions;
            double dP = (int)dinnerPortions;
            double sP = (int)supperPortions;

            double difference;
            difference = breakfastPortions - bP;
            if(difference < 0.25) {
                breakfastPortions = bP;
            } else if ((difference >= 0.25) && (difference < 0.75)) {
                breakfastPortions = bP + 0.5;
            } else if (difference >= 0.75) {
                breakfastPortions = bP + 1;
            }

            difference = secondMealPortions - smP;
            if(difference < 0.25) {
                secondMealPortions = smP;
            } else if ((difference >= 0.25) && (difference < 0.75)) {
                secondMealPortions = smP + 0.5;
            } else if (difference >= 0.75) {
                secondMealPortions = smP + 1;
            }

            difference = dinnerPortions - dP;
            if(difference < 0.25) {
                dinnerPortions = dP;
            } else if ((difference >= 0.25) && (difference < 0.75)) {
                dinnerPortions =dP + 0.5;
            } else if (difference >= 0.75) {
                dinnerPortions = dP + 1;
            }

            difference = supperPortions - sP;
            if(difference < 0.25) {
                supperPortions = sP;
            } else if ((difference >= 0.25) && (difference < 0.75)) {
                supperPortions =sP + 0.5;
            } else if (difference >= 0.75) {
                supperPortions = sP + 1;
            }

            recipeInfos.add(new RecipeInfo(breakfastPortions, breakfastRecipe, menu));
            recipeInfos.add(new RecipeInfo(secondMealPortions, secondMealRecipe, menu));
            recipeInfos.add(new RecipeInfo(dinnerPortions, dinnerRecipe, menu));
            recipeInfos.add(new RecipeInfo(supperPortions, supperRecipe, menu));
        }
        else if (menu.getMenuType().equals(MenuType.WEEKLY_MENU)) {
            for (int i = 0; i < 7; i++) {
                int breakfastNumber = rand.nextInt(numberOfBreakfasts);
                int secondMealNumber = rand.nextInt(numberOfSecondMeals);
                int dinnerNumber = rand.nextInt(numberOfDinners);
                int supperNumber = rand.nextInt(numberOfSuppers);

                Recipe breakfastRecipe = breakfasts.get(breakfastNumber);
                Recipe secondMealRecipe = secondMeals.get(secondMealNumber);
                Recipe dinnerRecipe = dinners.get(dinnerNumber);
                Recipe supperRecipe = suppers.get(supperNumber);

                double breakfastRecipeCalories = breakfastRecipe.getCaloriesForPortion();
                double secondMealRecipeCalories = secondMealRecipe.getCaloriesForPortion();
                double dinnerRecipeCalories = dinnerRecipe.getCaloriesForPortion();
                double supperRecipeCalories = supperRecipe.getCaloriesForPortion();

                double breakfastPortions = userBreakfastsCalories / breakfastRecipeCalories;
                double secondMealPortions = userSecondMealsCalories / secondMealRecipeCalories;
                double dinnerPortions = userDinnersCalories / dinnerRecipeCalories ;
                double supperPortions = userSuppersCalories / supperRecipeCalories;

                double bP = (int) breakfastPortions;
                double smP = (int) secondMealPortions;
                double dP = (int) dinnerPortions;
                double sP = (int) supperPortions;

                double difference;
                difference = breakfastPortions - bP;
                if (difference < 0.25) {
                    breakfastPortions = bP;
                } else if ((difference >= 0.25) && (difference < 0.75)) {
                    breakfastPortions = bP + 0.5;
                } else if (difference >= 0.75) {
                    breakfastPortions = bP + 1;
                }

                difference = secondMealPortions - smP;
                if (difference < 0.25) {
                    secondMealPortions = smP;
                } else if ((difference >= 0.25) && (difference < 0.75)) {
                    secondMealPortions = smP + 0.5;
                } else if (difference >= 0.75) {
                    secondMealPortions = smP + 1;
                }

                difference = dinnerPortions - dP;
                if (difference < 0.25) {
                    dinnerPortions = dP;
                } else if ((difference >= 0.25) && (difference < 0.75)) {
                    dinnerPortions = dP + 0.5;
                } else if (difference >= 0.75) {
                    dinnerPortions = dP + 1;
                }

                difference = supperPortions - sP;
                if (difference < 0.25) {
                    supperPortions = sP;
                } else if ((difference >= 0.25) && (difference < 0.75)) {
                    supperPortions = sP + 0.5;
                } else if (difference >= 0.75) {
                    supperPortions = sP + 1;
                }

                recipeInfos.add(new RecipeInfo(breakfastPortions, breakfastRecipe, menu));
                recipeInfos.add(new RecipeInfo(secondMealPortions, secondMealRecipe, menu));
                recipeInfos.add(new RecipeInfo(dinnerPortions, dinnerRecipe, menu));
                recipeInfos.add(new RecipeInfo(supperPortions, supperRecipe, menu));

                breakfasts.remove(breakfastNumber);
                secondMeals.remove(secondMealNumber);
                dinners.remove(dinnerNumber);
                suppers.remove(supperNumber);

                numberOfBreakfasts = breakfasts.size();
                numberOfSecondMeals = secondMeals.size();
                numberOfDinners = dinners.size();
                numberOfSuppers = suppers.size();
            }
        }
        return recipeInfos;
    }

}
