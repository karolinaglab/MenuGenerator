package pl.karolinaglab.menugenerator.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ingrName;

    private int calories;

    @OneToMany(mappedBy = "ingredient")
    private Set<Ingredient_info> recipes = new HashSet<>();

}
