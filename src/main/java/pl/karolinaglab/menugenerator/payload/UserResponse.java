package pl.karolinaglab.menugenerator.payload;

import pl.karolinaglab.menugenerator.enumTypes.Activity;
import pl.karolinaglab.menugenerator.enumTypes.Sex;
import pl.karolinaglab.menugenerator.model.User;

public class UserResponse {
    private int id;
    private String username;
    private String email;
    private double bodyWeight;
    private double height;
    private int age;
    private double physicalActivityLevel;
    private Activity activity;
    private Sex sex;
    private double totalEnergyExpenditure;
    private double BMI;
    private String message;

    public UserResponse(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.bodyWeight = user.getBodyWeight();
        this.height = user.getHeight();
        this.age = user.getAge();
        this.physicalActivityLevel = user.getPhysicalActivityLevel();
        this.activity = user.getActivity();
        this.sex = user.getSex();
        double totalEnergyExpenditure = user.getTotalEnergyExpenditure();
        this.totalEnergyExpenditure = Math.round(totalEnergyExpenditure * 100.0) / 100.0;
        double BMI = user.getBodyWeight() / (Math.pow((user.getHeight()/100),2));
        this.BMI = Math.round(BMI * 100.0) / 100.0;
        if (this.BMI < 18.5) {
            this.message = "Twoje BMI jest niższe niż 18.5 a to oznacza niedowagę";
        } else if (this.BMI >= 18.5 && this.BMI <= 24.9) {
            this.message = "Brawo! Twoja masa jest prawidłowa";
        } else if (this.BMI > 24.9 && this.BMI <=29.9) {
            this.message = "BMI powyżej 25 oznacza nadwagę. Popracuj nad sobą!";
        } else if (this.BMI > 29.9) {
            this.message = "BMI powyżej 30 oznacza otyłość. Nie lekceważ tego!";
        }
    }

    public int getId() {
        return id;
    }

    public double getTotalEnergyExpenditure() {
        return totalEnergyExpenditure;
    }

    public double getBMI() {
        return BMI;
    }

    public String getMessage() {
        return message;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public double getBodyWeight() {
        return bodyWeight;
    }

    public double getHeight() {
        return height;
    }

    public int getAge() {
        return age;
    }

    public double getPhysicalActivityLevel() {
        return physicalActivityLevel;
    }

    public Activity getActivity() {
        return activity;
    }

    public Sex getSex() {
        return sex;
    }
}
