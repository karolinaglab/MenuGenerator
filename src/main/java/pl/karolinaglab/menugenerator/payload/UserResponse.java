package pl.karolinaglab.menugenerator.payload;

import pl.karolinaglab.menugenerator.model.User;

public class UserResponse {
    private int id;
    private double totalEnergyExpenditure;
    private double BMI;
    private String message;

    public UserResponse(User user) {
        this.id = user.getId();
        this.totalEnergyExpenditure = user.getTotalEnergyExpenditure();
        this.BMI = user.getBodyWeight() / (Math.pow((user.getHeight()/100),2));
        if (this.BMI < 18.5) {
            this.message = "Niedowaga";
        } else if (this.BMI >= 18.5 && this.BMI <= 24.9) {
            this.message = "Waga prawidłowa";
        } else if (this.BMI > 24.9 && this.BMI <=29.9) {
            this.message = "Nadwaga";
        } else if (this.BMI > 29.9) {
            this.message = "Otyłość";
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
}
