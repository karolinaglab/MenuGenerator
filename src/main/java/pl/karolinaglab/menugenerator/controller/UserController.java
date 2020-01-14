package pl.karolinaglab.menugenerator.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.karolinaglab.menugenerator.enumTypes.Activity;
import pl.karolinaglab.menugenerator.enumTypes.Sex;
import pl.karolinaglab.menugenerator.model.User;
import pl.karolinaglab.menugenerator.service.UserService;

import java.util.Map;

@RestController
public class UserController {

    final private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/user")
    public User addUser(@RequestBody Map<String,String> body) {
        return userService.createNewUser(body);
    }


}
