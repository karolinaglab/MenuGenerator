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
    private int menuId;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private MenuType menuType;

   /* @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user; */

    @ManyToOne
    @JoinColumn(name="savedMenu_id", nullable=false)
    private SavedMenus savedMenus;

    @OneToMany(mappedBy = "menu")
    private Set<RecipeInfo> recipeInfos = new HashSet<>();


    /* @ManyToMany
    @JoinTable(
            name = "menu_recipes",
            joinColumns = @JoinColumn(name = "menuId"),
            inverseJoinColumns = @JoinColumn(name = "recipeId"))
    private Set<Recipe> recipes = new HashSet<>(); */


    public Menu() {
    }

    public Menu(MenuType menuType) {
        this.menuType = menuType;
    }

    public int getMenuId() {
        return menuId;
    }

    public MenuType getMenuType() {
        return menuType;
    }

    public void setMenuType(MenuType menuType) {
        this.menuType = menuType;
    }

    public SavedMenus getSavedMenus() {
        return savedMenus;
    }

    public void setSavedMenus(SavedMenus savedMenus) {
        this.savedMenus = savedMenus;
    }

    public Set<RecipeInfo> getRecipeInfos() {
        return recipeInfos;
    }
   /* public Set<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(Set<Recipe> recipes) {
        this.recipes = recipes;
    } */
}
