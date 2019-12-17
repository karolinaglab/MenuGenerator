package pl.karolinaglab.menugenerator.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="Ingredient_info")
@IdClass(Ingredient_infoPK.class)
public class Ingredient_info{

    @Id
    private int recipeId;
    @Id
    private int ingrId;

    private double amount;

    @ManyToOne
    @JoinColumn(name = "recipeId", updatable = false, insertable = false,
            referencedColumnName = "id")
    private Recipe recipe;

    @ManyToOne
    @JoinColumn(name = "ingrId", updatable = false, insertable = false,
            referencedColumnName = "id")
    private Ingredient ingredient;


}
