package com.appsdeveloper.app.ws.mobileappws.ui.model.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UpdateUserDetailsRequestModel {

    @NotBlank(message = "The First Name can not be null")
    @Size(min = 2, message = "The first name must be equal or greater than 2")
    private String firstName;

    @NotBlank(message = "The Last Name can not be null")
    @Size(min = 2, message = "The last name must be equal or greater than 2")
    private String lastName;
}
