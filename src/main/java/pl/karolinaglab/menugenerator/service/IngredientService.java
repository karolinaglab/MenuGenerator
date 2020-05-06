package pl.karolinaglab.menugenerator.service;

import org.springframework.stereotype.Service;
import pl.karolinaglab.menugenerator.enumTypes.AmountType;
import pl.karolinaglab.menugenerator.exceptions.ResourceAlreadyExistException;
import pl.karolinaglab.menugenerator.exceptions.ResourceNotFoundException;
import pl.karolinaglab.menugenerator.model.Ingredient;
import pl.karolinaglab.menugenerator.payload.IngredientResponse;
import pl.karolinaglab.menugenerator.repository.IngredientRepository;
import pl.karolinaglab.menugenerator.repository.IngredientInfoRepository;

import java.util.*;

@Service
public class IngredientService {

    final private IngredientRepository ingredientRepository;
    final private IngredientInfoRepository ingredientInfoRepository;

    public IngredientService(IngredientRepository ingredientRepository, IngredientInfoRepository ingredient_infoRepository) {
        this.ingredientRepository = ingredientRepository;
        this.ingredientInfoRepository = ingredient_infoRepository;
    }

    public Ingredient createIngredient(String ingredientName, String amountTypeString, String caloriesString) throws Exception{
        AmountType amountType = AmountType.valueOf(amountTypeString);

        double calories = Double.parseDouble(caloriesString);

        Optional<Ingredient> ingredient = ingredientRepository.findByIngrName(ingredientName);

        if(ingredient.isEmpty()) {
            return ingredientRepository.save(new Ingredient(ingredientName, amountType, calories));

        } else {
            throw new ResourceAlreadyExistException("Ingredient already exist : "+ ingredientName);
        }
    }

    public Ingredient getIngredientByName(String name) throws Exception{
        Optional<Ingredient> ingredient = ingredientRepository.findByIngrName(name);
        if(ingredient.isPresent()) {
            return ingredient.get();

        } else {
            throw new ResourceNotFoundException("Ingredient not found on : "+ name);
        }
    }

    public IngredientResponse getIngredientWithCalories(String name) throws Exception{
        Optional<Ingredient> ingredient = ingredientRepository.findByIngrName(name);
        if(ingredient.isPresent()) {
            return new IngredientResponse(ingredient.get());

        } else {
            throw new ResourceNotFoundException("Ingredient not found on : "+ name);
        }
    }

//    public List<Ingredient> getIngredients() {
//
//        return ingredientRepository.findAll();
//    }

    public List<IngredientResponse> getIngredients(String name) {
        List<IngredientResponse> ingredientResponses = new ArrayList<>();
        List<Ingredient> ingredients = ingredientRepository.findByIngrNameContaining(name);
        for (Ingredient ingredient : ingredients) {
            ingredientResponses.add(new IngredientResponse(ingredient));
        }
        return ingredientResponses;
    }

    public List<IngredientResponse> getAllIngredients() {

        List<IngredientResponse> ingredientResponses = new ArrayList<>();
        List<Ingredient> ingredients = ingredientRepository.findAll();
        for (Ingredient ingredient : ingredients) {
            ingredientResponses.add(new IngredientResponse(ingredient));
        }
        return ingredientResponses;
    }

    public Map<String, Boolean> deleteIngredient(String id) throws Exception {
        int ingredientId = Integer.parseInt(id);
        Optional<Ingredient> ingredient = ingredientRepository.findById(ingredientId);
        if(ingredient.isPresent()) {
            Map<String, Boolean> response = new HashMap<>();
            if(ingredientInfoRepository.findAllByIngredientId(ingredientId).isEmpty()) {
                ingredientRepository.deleteById(ingredientId);
                response.put("deleted", Boolean.TRUE);
                return response;
            } else {
                throw new ResourceAlreadyExistException("Ingredient found in recipes, You can't delete it!");
            }

        } else {
            throw new ResourceNotFoundException("Ingredient not found on : "+ ingredientId);
        }
    }

}
