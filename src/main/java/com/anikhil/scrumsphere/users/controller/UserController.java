package com.anikhil.scrumsphere.users.controller;

import com.anikhil.scrumsphere.http.BaseController;
import com.anikhil.scrumsphere.users.models.User;
import com.anikhil.scrumsphere.users.services.UserService;
import com.anikhil.scrumsphere.utils.JsonUtils;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController extends BaseController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService, JsonUtils jsonUtils) {
        super(jsonUtils);
        this.userService = userService;
    }

    @PostMapping("/register")
    public JsonNode createUser(@RequestBody JsonNode payload) {
        User user = this.jsonUtils.fromJsonSilently(payload.toString(), User.class);
        return sendSuccessResponse(this.userService.createUser(user));
    }

    @PostMapping("/login")
    public JsonNode login(@RequestBody JsonNode payload) {
        String userId = payload.get("userId").asText();
        String password = payload.get("password").asText();
        return sendSuccessResponse(this.userService.findUserByIdAndPassword(userId, password));
    }

    @GetMapping("/checkUserId")
    public JsonNode checkUserId(@RequestParam String userId) {
        return sendSuccessResponse(this.userService.checkUserId(userId));
    }
}
