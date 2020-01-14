package pl.karolinaglab.menugenerator.service;

import org.springframework.stereotype.Service;
import pl.karolinaglab.menugenerator.creators.MenuCreator;
import pl.karolinaglab.menugenerator.enumTypes.FoodPreferences;
import pl.karolinaglab.menugenerator.enumTypes.MenuType;
import pl.karolinaglab.menugenerator.enumTypes.RecipeType;
import pl.karolinaglab.menugenerator.exceptions.ResourceNotFoundException;
import pl.karolinaglab.menugenerator.model.Menu;
import pl.karolinaglab.menugenerator.model.Recipe;
import pl.karolinaglab.menugenerator.model.RecipeInfo;
import pl.karolinaglab.menugenerator.model.User;
import pl.karolinaglab.menugenerator.repository.MenuRepository;
import pl.karolinaglab.menugenerator.repository.RecipeInfoRepository;
import pl.karolinaglab.menugenerator.repository.RecipeRepository;
import pl.karolinaglab.menugenerator.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class MenuService {

    final private MenuRepository menuRepository;
    final private RecipeRepository recipeRepository;
    final private RecipeInfoRepository recipeInfoRepository;
    final private UserRepository userRepository;

    public MenuService(MenuRepository menuRepository, RecipeRepository recipeRepository, RecipeInfoRepository recipeInfoRepository, UserRepository userRepository) {
        this.menuRepository = menuRepository;
        this.recipeRepository = recipeRepository;
        this.recipeInfoRepository = recipeInfoRepository;
        this.userRepository = userRepository;
    }

    public Menu createMenu(Map<String,String> body) throws Exception{
        String idString = body.get("id");
        String menuTypeString = body.get("menuType");
        String foodPreferencesString = body.get("foodPreferences");

        int userID = Integer.parseInt(idString);
        MenuType menuType = MenuType.valueOf(menuTypeString);
        FoodPreferences foodPreferences = FoodPreferences.valueOf(foodPreferencesString);

        List<Recipe> breakfasts = new ArrayList<>();
        List<Recipe> secondMeals = new ArrayList<>();
        List<Recipe> dinners = new ArrayList<>();
        List<Recipe> suppers = new ArrayList<>();
        if (foodPreferences.equals(FoodPreferences.ALL)) {
            breakfasts = recipeRepository.findByRecipeType(RecipeType.BREAKFAST);
            secondMeals = recipeRepository.findByRecipeType(RecipeType.SECOND_MEAL);
            dinners = recipeRepository.findByRecipeType(RecipeType.DINNER);
            suppers = recipeRepository.findByRecipeType(RecipeType.SUPPER);
        } else if (foodPreferences.equals(FoodPreferences.GLUTEN_FREE)) {
            breakfasts = recipeRepository.findAllByRecipeTypeAndGlutenFreeTrue(RecipeType.BREAKFAST);
            secondMeals = recipeRepository.findAllByRecipeTypeAndGlutenFreeTrue(RecipeType.SECOND_MEAL);
            dinners = recipeRepository.findAllByRecipeTypeAndGlutenFreeTrue(RecipeType.DINNER);
            suppers = recipeRepository.findAllByRecipeTypeAndGlutenFreeTrue(RecipeType.SUPPER);
        } else if (foodPreferences.equals(FoodPreferences.LACTOSE_FREE)) {
            breakfasts = recipeRepository.findAllByRecipeTypeAndLactoseFreeTrue(RecipeType.BREAKFAST);
            secondMeals = recipeRepository.findAllByRecipeTypeAndLactoseFreeTrue(RecipeType.SECOND_MEAL);
            dinners = recipeRepository.findAllByRecipeTypeAndLactoseFreeTrue(RecipeType.DINNER);
            suppers = recipeRepository.findAllByRecipeTypeAndLactoseFreeTrue(RecipeType.SUPPER);
        } else if (foodPreferences.equals(FoodPreferences.VEGETARIAN)) {
            breakfasts = recipeRepository.findAllByRecipeTypeAndVegetarianTrue(RecipeType.BREAKFAST);
            secondMeals = recipeRepository.findAllByRecipeTypeAndVegetarianTrue(RecipeType.SECOND_MEAL);
            dinners = recipeRepository.findAllByRecipeTypeAndVegetarianTrue(RecipeType.DINNER);
            suppers = recipeRepository.findAllByRecipeTypeAndVegetarianTrue(RecipeType.SUPPER);
        }

        Optional<User> userFromDatabase = userRepository.findById(userID);
        if (userFromDatabase.isPresent()) {
            User user = userFromDatabase.get();
            Menu newMenu = new Menu(menuType, foodPreferences, user);
            double userCalories = user.getTotalEnergyExpenditure();
            menuRepository.save(newMenu);
            List<RecipeInfo> recipeInfos = MenuCreator.createMenu(breakfasts, secondMeals, dinners, suppers, userCalories, newMenu);
            for (RecipeInfo recipeInfo : recipeInfos) {
                recipeInfoRepository.save(recipeInfo);
            }
            return menuRepository.save(newMenu);

        } else {
            throw new ResourceNotFoundException("User not found on : "+ idString);
        }
    }

    public Menu getMenu(int id) throws Exception{
        Optional<Menu> menu = menuRepository.findById(id);
        if (menu.isPresent()) {
            return menu.get();
        } else {
            throw new ResourceNotFoundException("Menu not found on : " + id);
        }
    }
}
