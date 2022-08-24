package com.atos.apps.photo.app.api.users.ui.users.data;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsersRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findUserEntitiesByEmail(String email);
    UserEntity findByUserId(String userId);
}
