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
    private Long id;

    private String nickname;
    private String password;

    private double bodyWeight;
    private double height;
    private int age;
    private double basalMetabolicRate;
    private double physicalActivityLevel;
    private double totalEnergyExpenditure;
    @Enumerated(EnumType.STRING)
    private Activity activity;
    @Enumerated(EnumType.STRING)
    private Sex sex;


   /* @OneToMany(mappedBy = "user")
    private Set<Menu> menus = new HashSet<>();*/

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shoppingList_id", referencedColumnName = "id")
    private ShoppingList shoppingList;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "savedMenus_id", referencedColumnName = "id")
    private SavedMenus savedMenus;


    public User(String nickname, String password, double bodyWeight, double height, int age, Activity activity, Sex sex) {
        this.nickname = nickname;
        this.password = password;
        this.bodyWeight = bodyWeight;
        this.height = height;
        this.age = age;
        this.activity = activity;
        this.sex = sex;

        if(activity.equals(LOW)) {
            this.physicalActivityLevel = 1.55;
        } else if (activity.equals(AVERAGE)) {
            this.physicalActivityLevel = 1.85;
        } else if (activity.equals(HIGH)) {
            this.physicalActivityLevel = 2.2;
        }

        if (sex.equals(MALE)) {
            this.basalMetabolicRate = 66.47 + 13.7*this.bodyWeight + 5.0*this.height - 6.76 * this.age;
        } else if (sex.equals(FEMALE)) {
            this.basalMetabolicRate = 65.51 + 9.567*this.bodyWeight + 1.85*this.height - 4.68*this.age;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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
}
