package pl.karolinaglab.menugenerator.model;

import pl.karolinaglab.menugenerator.enumTypes.RecipeType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String recipeName;
    private RecipeType recipeType;


    @ManyToMany(mappedBy = "recipes")
    private Set<Menu> menus = new HashSet<>();

    @OneToMany(mappedBy = "recipe")
    private Set<Ingredient_info> ingredients = new HashSet<>();

}
