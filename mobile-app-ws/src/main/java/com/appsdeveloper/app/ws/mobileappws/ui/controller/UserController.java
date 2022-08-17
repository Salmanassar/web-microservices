package com.appsdeveloper.app.ws.mobileappws.ui.controller;

import com.appsdeveloper.app.ws.mobileappws.ui.model.request.UpdateUserDetailsRequestModel;
import com.appsdeveloper.app.ws.mobileappws.ui.model.request.UserDetailsRequestModel;
import com.appsdeveloper.app.ws.mobileappws.ui.model.response.UserRest;
import com.appsdeveloper.app.ws.mobileappws.ui.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    private Map<String, UserRest> users;
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getUser(@RequestParam(value = "page", defaultValue = "1") int page,
                          @RequestParam(value = "limit", defaultValue = "50") int limit,
                          @RequestParam(value = "sort", defaultValue = "desc", required = false) String sort) {
        return "the user was called with page = " + page + " and limit = " + limit + " and sort = " + sort;
    }

    @GetMapping(path = "{userId}",
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserRest> getUserById(@PathVariable String userId) {
        Optional<UserRest> userRest = userService.getUserById(userId);
        return userRest.map(rest -> new ResponseEntity<>(rest, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));

    }

    @PostMapping(consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetailsRequestModel userDetails) {
        UserRest returnValue = userService.createUser(userDetails);
        return new ResponseEntity<>(returnValue, HttpStatus.OK);
    }

    @PutMapping(path = "{userId}", consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserRest> updateUser(@PathVariable String userId,
                                               @Valid @RequestBody UpdateUserDetailsRequestModel updateUserDetails) {
        UserRest updateUserRest = userService.updateUser(userId, updateUserDetails);
        return new ResponseEntity<>(updateUserRest, HttpStatus.OK);
    }

    @DeleteMapping(path = "{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
        userService.removeUser(userId);
        return ResponseEntity.noContent().build();
    }
}
