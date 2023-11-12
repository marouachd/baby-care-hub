package co.simplon.babycarehub.services;

import co.simplon.babycarehub.dtos.UserDetail;
import co.simplon.babycarehub.dtos.UserDto;
import co.simplon.babycarehub.dtos.UserUpdateDto;

public interface UserService {

    void create(UserDto inputs);

    UserDetail detail(Long id);

    void update(Long id, UserUpdateDto inputs);

}
