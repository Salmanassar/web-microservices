package com.atos.apps.photo.app.api.users.ui.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserResponseModel {
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
}
