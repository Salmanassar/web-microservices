package com.appsdeveloper.app.ws.mobileappws.ui.model.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserDetailsRequestModel {
    @NotBlank(message = "Id can not be null")
    private String userId;

    @NotBlank(message = "The First Name can not be null")
    @Size(min = 2, message = "The first name must be equal or greater than 2")
    private String firstName;

    @NotBlank(message = "The Last Name can not be null")
    @Size(min = 2, message = "The last name must be equal or greater than 2")
    private String lastName;

    @Email
    private String email;
    @NotBlank(message = "The password can not be null")

    @Size(min = 8, max = 16, message = "The Password must be equal or greater than 8 and less of equal 14")
    private String password;
}
