package com.appsdeveloper.app.ws.mobileappws.ui.services;

import com.appsdeveloper.app.ws.mobileappws.ui.model.request.UpdateUserDetailsRequestModel;
import com.appsdeveloper.app.ws.mobileappws.ui.model.request.UserDetailsRequestModel;
import com.appsdeveloper.app.ws.mobileappws.ui.model.response.UserRest;


import java.util.Optional;

public interface UserService {
   Optional<UserRest> getUserById(String userId);
   UserRest createUser(UserDetailsRequestModel userDetails);
   UserRest updateUser(String userId, UpdateUserDetailsRequestModel updateUserDetails);
   void removeUser(String userId);
}
