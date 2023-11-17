package co.simplon.babycarehub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.babycarehub.dtos.SignInCredentials;
import co.simplon.babycarehub.dtos.TokenInfo;
import co.simplon.babycarehub.services.AuthService;

@RestController
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthService service;

    @PostMapping("/sign-in")
    public TokenInfo signIn(
	    @RequestBody SignInCredentials inputs) {
	return service.signIn(inputs);
    }
}
