package pl.karolinaglab.menugenerator.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.karolinaglab.menugenerator.enumTypes.Activity;
import pl.karolinaglab.menugenerator.enumTypes.Sex;
import pl.karolinaglab.menugenerator.model.User;
import pl.karolinaglab.menugenerator.repository.UserRepository;

import java.util.Map;

@RestController
public class UserController {

    private UserRepository userRepository;

    public UserController(UserRepository repository) {
        this.userRepository = repository;
    }

    @PostMapping("/user")
    public User addUser(@RequestBody Map<String,String> body) {
        String nickname = body.get("nickname");
        String password = body.get("password");
        String heightString = body.get("height");
        String bodyWeightString = body.get("bodyWeight");
        String ageString = body.get("age");
        String activityString = body.get("activity");
        String sexString = body.get("sex");

        double height = Double.parseDouble(heightString);
        double bodyWeight = Double.parseDouble(bodyWeightString);
        int age = Integer.parseInt(ageString);
        Activity activity = Activity.valueOf(activityString);
        Sex sex = Sex.valueOf(sexString);

        User newUser = new User(nickname, password, bodyWeight, height, age, activity, sex);

        return userRepository.save(newUser);
    }


}
