package com.appsdeveloper.app.ws.mobileappws.ui.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomExceptionMassage {
    private Date timestamp;
    private String massage;
}
