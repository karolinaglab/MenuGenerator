package pl.karolinaglab.menugenerator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.karolinaglab.menugenerator.enumTypes.AmountType;
import pl.karolinaglab.menugenerator.exceptions.ResourceAlreadyExistException;
import pl.karolinaglab.menugenerator.exceptions.ResourceNotFoundException;
import pl.karolinaglab.menugenerator.model.Ingredient;
import pl.karolinaglab.menugenerator.repository.IngredientRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class IngredientService {

    final private IngredientRepository ingredientRepository;

    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
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

    public List<Ingredient> getIngredients(String name) {
        return ingredientRepository.findByIngrNameContaining(name);
    }

    public Map<String, Boolean> deleteIngredient(String id) throws Exception {
        int ingredientId = Integer.parseInt(id);
        Optional<Ingredient> ingredient = ingredientRepository.findById(ingredientId);
        if(ingredient.isPresent()) {
            ingredientRepository.deleteById(ingredientId);
            Map<String, Boolean> response = new HashMap<>();
            response.put("deleted", Boolean.TRUE);
            return response;

        } else {
            throw new ResourceNotFoundException("Ingredient not found on : "+ ingredientId);
        }
    }

}
