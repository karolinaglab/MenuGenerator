package pl.karolinaglab.menugenerator.model;

import pl.karolinaglab.menugenerator.enumTypes.MenuType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long menuId;

    private MenuType menuType;

   /* @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user; */

    @ManyToOne
    @JoinColumn(name="savedMenu_id", nullable=false)
    private SavedMenus savedMenus;

    @ManyToMany
    @JoinTable(
            name = "menu_recipes",
            joinColumns = @JoinColumn(name = "menuId"),
            inverseJoinColumns = @JoinColumn(name = "recipeId"))
    private Set<Recipe> recipes = new HashSet<>();
}
