package pl.karolinaglab.menugenerator.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.karolinaglab.menugenerator.enumTypes.Activity;
import pl.karolinaglab.menugenerator.enumTypes.Sex;
import pl.karolinaglab.menugenerator.exceptions.ResourceNotFoundException;
import pl.karolinaglab.menugenerator.model.User;
import pl.karolinaglab.menugenerator.payload.UserResponse;
import pl.karolinaglab.menugenerator.security.CurrentUser;
import pl.karolinaglab.menugenerator.security.UserPrincipal;
import pl.karolinaglab.menugenerator.service.UserService;

import java.util.Map;

@RestController
public class UserController {

    final private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/user")
    public UserResponse getUser(@CurrentUser UserPrincipal currentUser) throws ResourceNotFoundException {
        return userService.getUser(currentUser);
    }
}
