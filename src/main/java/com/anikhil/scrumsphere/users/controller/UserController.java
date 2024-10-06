package com.anikhil.scrumsphere.users.controller;

import com.anikhil.scrumsphere.http.BaseController;
import com.anikhil.scrumsphere.users.services.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController extends BaseController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public JsonNode createUser(@RequestBody JsonNode payload) {
        String userId = payload.get("userId").asText();
        String password = payload.get("password").asText();

        return sendSuccessResponse(this.userService.createUser(userId, password));
    }

    @GetMapping("/getUser")
    public JsonNode getUser(@RequestParam String userId) {
        return sendSuccessResponse(this.userService.findUserByUserId(userId));
    }
}
