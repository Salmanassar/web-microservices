package com.appsdeveloper.app.ws.mobileappws.ui.services.impl;


import com.appsdeveloper.app.ws.mobileappws.ui.model.request.UpdateUserDetailsRequestModel;
import com.appsdeveloper.app.ws.mobileappws.ui.model.request.UserDetailsRequestModel;
import com.appsdeveloper.app.ws.mobileappws.ui.model.response.UserRest;
import com.appsdeveloper.app.ws.mobileappws.ui.services.UserService;
import com.appsdeveloper.app.ws.mobileappws.ui.utils.GenerateUserIdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private Map<String, UserRest> users;
    private GenerateUserIdUtil generateUserIdUtil;

    @Autowired
    public UserServiceImpl(GenerateUserIdUtil generateUserIdUtil) {
        this.generateUserIdUtil = generateUserIdUtil;
    }

    @Override
    public Optional<UserRest> getUserById(String userId) {
        return Optional.ofNullable(users.get(userId));
    }

    @Override
    public UserRest createUser(UserDetailsRequestModel userDetails) {
        UserRest returnValue = new UserRest();
        returnValue.setEmail(userDetails.getEmail());
        returnValue.setFirstName(userDetails.getFirstName());
        returnValue.setLastName(userDetails.getLastName());
        returnValue.setPassword(userDetails.getPassword());
        String userId = generateUserIdUtil.generateUserId();
        returnValue.setUserId(userId);
        if (users == null) {
            users = new HashMap<>();
        }
        users.put(userId, returnValue);
        return returnValue;
    }

    @Override
    public UserRest updateUser(String userId, UpdateUserDetailsRequestModel updateUserDetails) {
        UserRest updateUserRest = users.get(userId);
        updateUserRest.setFirstName(updateUserDetails.getFirstName());
        updateUserRest.setLastName(updateUserDetails.getLastName());
        users.put(userId, updateUserRest);
        return updateUserRest;
    }

    @Override
    public void removeUser(String userId) {
        users.remove(userId);
    }
}
