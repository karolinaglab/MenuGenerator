package pl.karolinaglab.menugenerator.controller;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.karolinaglab.menugenerator.payload.LoginRequest;
import pl.karolinaglab.menugenerator.payload.SignUpRequest;
import pl.karolinaglab.menugenerator.service.AuthenticationService;

import javax.validation.Valid;


@RestController
@RequestMapping("/auth")
public class AuthenticationController {


    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        return authenticationService.authenticateUser(loginRequest);

    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {

        return authenticationService.registerUser(signUpRequest);

    }
}
