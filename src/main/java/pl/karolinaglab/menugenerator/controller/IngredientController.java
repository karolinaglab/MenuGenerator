package pl.karolinaglab.menugenerator.controller;

import org.springframework.web.bind.annotation.*;
import pl.karolinaglab.menugenerator.model.Ingredient;

import pl.karolinaglab.menugenerator.service.IngredientService;


import java.util.List;
import java.util.Map;


@RestController
public class IngredientController {

    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
       this.ingredientService = ingredientService;
    }

    @PostMapping("/ingredient")
    public Ingredient addIngredient(@RequestBody Map<String,String> body) throws Exception{

        String ingredientName = body.get("ingredientName");
        String amountTypeString = body.get("amountType");
        String caloriesString = body.get("calories");

        return ingredientService.createIngredient(ingredientName, amountTypeString, caloriesString);
    }

    @GetMapping("/ingredient/{name}")
    public Ingredient getIngredient(@PathVariable String name) throws Exception {
        return ingredientService.getIngredientByName(name);
    }

    @GetMapping("/ingredient")
    public List<Ingredient> getListIngredient(@RequestParam String name) {
        return ingredientService.getIngredients(name);
    }


    @DeleteMapping("/ingredient/{id}")
    public Map<String, Boolean> deleteIngredient(@PathVariable String id) throws Exception {
        return ingredientService.deleteIngredient(id);
    }
}
