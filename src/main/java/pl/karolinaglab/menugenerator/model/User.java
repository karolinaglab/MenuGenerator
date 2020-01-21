package pl.karolinaglab.menugenerator.model;


import pl.karolinaglab.menugenerator.enumTypes.Activity;
import pl.karolinaglab.menugenerator.enumTypes.Sex;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static pl.karolinaglab.menugenerator.enumTypes.Activity.*;
import static pl.karolinaglab.menugenerator.enumTypes.Sex.FEMALE;
import static pl.karolinaglab.menugenerator.enumTypes.Sex.MALE;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;
    private String email;
    private String password;

    private double bodyWeight;
    private double height;
    private int age;
    private double basalMetabolicRate;
    private double physicalActivityLevel;
    private double totalEnergyExpenditure;
    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private Activity activity;
    @Enumerated(EnumType.STRING)
    @Column(length = 8)
    private Sex sex;


    @OneToMany(mappedBy = "user")
    private Set<Menu> menus = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shoppingList_id", referencedColumnName = "id")
    private ShoppingList shoppingList;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

   /* @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "savedMenus_id", referencedColumnName = "id")
    private SavedMenus savedMenus;*/


    public User() {
    }

    public User(String username, String password, String email, double bodyWeight, double height, int age, Activity activity, Sex sex) {
        this.username = username;
        this.password = password;
        this.bodyWeight = bodyWeight;
        this.height = height;
        this.age = age;
        this.activity = activity;
        this.sex = sex;
        this.email = email;

       setTotalEnergyExpenditure(this.activity, this.sex, this.bodyWeight, this.height, this.age);

    }

    public void setTotalEnergyExpenditure(Activity activity, Sex sex, double bodyWeight, double height, int age) {
        if (activity.equals(NONE)) {
            this.physicalActivityLevel = 1.2;
        } else if(activity.equals(LOW)) {
            this.physicalActivityLevel = 1.3;
        } else if (activity.equals(AVERAGE)) {
            this.physicalActivityLevel = 1.5;
        } else if (activity.equals(HIGH)) {
            this.physicalActivityLevel = 1.7;
        } else if (activity.equals(VERY_HIGH)) {
            this.physicalActivityLevel = 1.9;
        }

        if (sex.equals(MALE)) {
            this.basalMetabolicRate = 9.99*this.bodyWeight + 6.25*this.height - 4.92 * this.age + 5;
        } else if (sex.equals(FEMALE)) {
            this.basalMetabolicRate = 9.99*this.bodyWeight + 6.25*this.height - 4.92*this.age - 161;
        }

        this.totalEnergyExpenditure = this.basalMetabolicRate * this.physicalActivityLevel;
    }

    public double getBasalMetabolicRate() {
        return basalMetabolicRate;
    }

    public double getPhysicalActivityLevel() {
        return physicalActivityLevel;
    }

    public double getTotalEnergyExpenditure() {
        return totalEnergyExpenditure;
    }

   /* public Set<Menu> getMenus() {
        return menus;
    }*/

    public ShoppingList getShoppingList() {
        return shoppingList;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBodyWeight() {
        return bodyWeight;
    }

    public void setBodyWeight(double bodyWeight) {
        this.bodyWeight = bodyWeight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Set<Menu> getMenus() {
        return menus;
    }

    public String getEmail() {
        return email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
