package pl.karolinaglab.menugenerator.model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;
    private String password;


    public User(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }

    @OneToMany(mappedBy = "user")
    private Set<Menu> menus = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shoppingList_id", referencedColumnName = "id")
    private ShoppingList shoppingList;


}
