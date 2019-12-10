package pl.karolinaglab.menugenerator.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class SavedMenus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "savedMenus")
    private Set<Menu> menus = new HashSet<>();


    @OneToOne(mappedBy = "savedMenus")
    private User user;

    public Set<Menu> getMenus() {
        return menus;
    }

    public void setMenus(Set<Menu> menus) {
        this.menus = menus;
    }
}
