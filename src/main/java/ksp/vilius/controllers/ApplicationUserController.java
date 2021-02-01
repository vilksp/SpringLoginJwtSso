package ksp.vilius.controllers;

import ksp.vilius.dto.CreateUserDto;
import ksp.vilius.models.ApplicationUser;
import ksp.vilius.payload.JwtSuccessLoginResponse;
import ksp.vilius.payload.LoginRequest;
import ksp.vilius.services.UserServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class ApplicationUserController {

    @Autowired
    private UserServiceI userService;

    @PostMapping("/register")
    public ResponseEntity<ApplicationUser> registerNewUserToApplication(@RequestBody CreateUserDto userDto) {

        return new ResponseEntity<>(userService.registerNewUser(userDto), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtSuccessLoginResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {

        return new ResponseEntity<>(userService.authenticateUser(loginRequest), HttpStatus.OK);
    }

}
