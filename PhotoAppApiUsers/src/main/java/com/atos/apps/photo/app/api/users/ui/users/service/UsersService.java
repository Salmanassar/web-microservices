package com.atos.apps.photo.app.api.users.ui.users.service;

import com.atos.apps.photo.app.api.users.shared.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsersService extends UserDetailsService {
    UserDto createUser(UserDto userDetails);
    UserDto getUserDetailsByEmail(String email);
    UserDto getUserById(String userId);
}
