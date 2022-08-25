package com.atos.apps.photo.app.api.users.shared;

import com.atos.apps.photo.app.api.users.ui.model.AlbumResponseModel;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String encryptedPassword;
    private List<AlbumResponseModel> albums;

    @Override
    public String toString() {
        return "UserDto{" +
                "albums=" + albums +
                '}';
    }
}
