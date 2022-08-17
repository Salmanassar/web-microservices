package com.atos.apps.photo.app.api.users.ui.controllers;

import com.atos.apps.photo.app.api.users.shared.UserDto;
import com.atos.apps.photo.app.api.users.ui.model.CreateUserRequestModel;
import com.atos.apps.photo.app.api.users.ui.model.CreateUserResponseModel;
import com.atos.apps.photo.app.api.users.ui.users.service.UsersService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
@RequestMapping("/users")
public class UsersController {
    private Environment environment;
    private UsersService usersService;

    @Autowired
    public UsersController(Environment environment, UsersService usersService) {
        this.environment = environment;
        this.usersService = usersService;
    }

    @GetMapping("/check")
    public String status() throws UnknownHostException {
        return "Working " + " " + InetAddress.getLocalHost();
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<CreateUserResponseModel> createUser(@RequestBody CreateUserRequestModel requestModel) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserDto userDto = modelMapper.map(requestModel, UserDto.class);
        usersService.createUser(userDto);
        CreateUserResponseModel responseModel = modelMapper.map(userDto, CreateUserResponseModel.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseModel);
    }
}
