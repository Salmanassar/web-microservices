package com.atos.apps.photo.app.api.users.ui.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequestModel {

    @NotBlank(message = "The First Name can not be null")
    @Size(min = 2, message = "The first name must be equal or greater than 2")
    private String firstName;

    @NotBlank(message = "The Last Name can not be null")
    @Size(min = 2, message = "The last name must be equal or greater than 2")
    private String lastName;

    @Email
    public String email;

    @NotBlank(message = "The password can not be empty")
    @Size(min = 8, max = 16, message = "The password must be equal or greater than 8 and less than 16")
    public String password;
}
