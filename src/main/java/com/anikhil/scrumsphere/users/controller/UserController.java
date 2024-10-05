package com.anikhil.scrumsphere.users.controller;

import com.anikhil.scrumsphere.http.BaseController;
import com.anikhil.scrumsphere.users.services.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController extends BaseController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/create")
    public JsonNode createUser(@RequestParam String userId, @RequestParam String name, @RequestParam String email) {
        return sendSuccessResponse(this.userService.createUser(userId, name, email));
    }
}
