package com.appsdeveloper.app.ws.mobileappws.ui.utils;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GenerateUserIdUtil {
    public String generateUserId(){
        return UUID.randomUUID().toString();
    }
}
