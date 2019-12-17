package pl.karolinaglab.menugenerator.controller;

import org.springframework.web.bind.annotation.*;
import pl.karolinaglab.menugenerator.enumTypes.AmountType;
import pl.karolinaglab.menugenerator.exceptions.ResourceAlreadyExistException;
import pl.karolinaglab.menugenerator.exceptions.ResourceNotFoundException;
import pl.karolinaglab.menugenerator.model.Ingredient;
import pl.karolinaglab.menugenerator.repository.IngredientRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class IngredientController {

    private IngredientRepository ingredientRepository;

    public IngredientController(IngredientRepository repository) {
        this.ingredientRepository = repository;
    }

    @PostMapping("/ingredient")
    public Ingredient addIngredient(@RequestBody Map<String,String> body) throws Exception{

        String ingredientName = body.get("ingredientName");
        String amountTypeString = body.get("amountType");
        String caloriesString = body.get("calories");

        AmountType amountType = AmountType.valueOf(amountTypeString);

        int calories = Integer.parseInt(caloriesString);

        Optional<Ingredient> ingredient = ingredientRepository.findByIngrName(ingredientName);

        if(ingredient.isEmpty()) {
            return ingredientRepository.save(new Ingredient(ingredientName, amountType, calories));

        } else {
            throw new ResourceAlreadyExistException("Ingredient already exist : "+ ingredientName);
        }
    }

    @GetMapping("/ingredient/{name}")
    public Ingredient getIngredient(@PathVariable String name) throws Exception {
        Optional<Ingredient> ingredient = ingredientRepository.findByIngrName(name);
        if(ingredient.isPresent()) {
            return ingredient.get();

        } else {
            throw new ResourceNotFoundException("Ingredient not found on : "+ name);
        }
    }

    @GetMapping("/ingredient")
    public List<Ingredient> getListIngredient(@RequestParam String name) {
        return ingredientRepository.findByIngrNameContaining(name);
    }


    @DeleteMapping("/ingredient/{id}")
    public Map<String, Boolean> deleteIngredient(@PathVariable String id) throws Exception {

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
