package com.atos.account.account.management.microservice.ui.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/management")
public class AccountController {
    @GetMapping("/account")
    public String manager(){
        return "Management Account";
    }
}
