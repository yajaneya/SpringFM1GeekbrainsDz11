package ru.yajaneya.SpringFM1GeekbrainsDz11.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yajaneya.SpringFM1GeekbrainsDz11.entities.User;
import ru.yajaneya.SpringFM1GeekbrainsDz11.services.UserService;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class DemoController {
    private final UserService userService;

    @GetMapping("/")
    public String getHomePage () {
        return "home page";
    }

    @GetMapping("/user_info")
    public String daoTestPage(Principal principal) {
        System.out.println(principal);
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("Unable to find user by username: " + principal.getName()));
        return "Authenticated user info: " + user.getUsername() + ": " +user.getEmail();
    }

    @GetMapping("/users_info")
    public List<User> daoGetName() {

        return userService.findAllUsers();
    }

}
