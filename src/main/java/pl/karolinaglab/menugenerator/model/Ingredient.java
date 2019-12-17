package pl.karolinaglab.menugenerator.model;

import org.hibernate.annotations.NaturalId;
import pl.karolinaglab.menugenerator.enumTypes.AmountType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String ingrName;

    @Enumerated(EnumType.STRING)
    @Column(length = 8)
    private AmountType amountType;
    private int calories;

    @OneToMany(mappedBy = "ingredient")
    private Set<Ingredient_info> recipes = new HashSet<>();

    public Ingredient() {
    }

    public Ingredient(String ingrName, AmountType amountType, int calories) {
        this.ingrName = ingrName;
        this.amountType = amountType;
        this.calories = calories;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIngrName() {
        return ingrName;
    }

    public void setIngrName(String ingrName) {
        this.ingrName = ingrName;
    }

    public AmountType getAmountType() {
        return amountType;
    }

    public void setAmountType(AmountType amountType) {
        this.amountType = amountType;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }
}
