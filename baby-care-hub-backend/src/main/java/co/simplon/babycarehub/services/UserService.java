package co.simplon.babycarehub.services;

import java.util.List;

import co.simplon.babycarehub.dtos.UserDetail;
import co.simplon.babycarehub.dtos.UserDto;
import co.simplon.babycarehub.dtos.UserUpdateDto;
import co.simplon.babycarehub.entities.RoleEntity;
import co.simplon.babycarehub.entities.UserEntity;

public interface UserService {

    void create(UserDto inputs);

    UserDetail detail(Long id);

    void update(Long id, UserUpdateDto inputs);

    List<UserEntity> getAllChildminder(RoleEntity role);

    Boolean existsByEmail(String email);

    UserEntity findByEmail(String email);

}
