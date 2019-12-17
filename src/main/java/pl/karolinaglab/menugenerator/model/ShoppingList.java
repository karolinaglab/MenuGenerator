package pl.karolinaglab.menugenerator.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ShoppingList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

   // private List<Ingredient> shoppingList = new ArrayList<>();

    @OneToOne(mappedBy = "shoppingList")
    private User user;

    public ShoppingList() {
    }
}
