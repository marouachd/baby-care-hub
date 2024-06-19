package co.simplon.babycarehub.dtos;

import co.simplon.babycarehub.entities.PersonEntity;
import co.simplon.babycarehub.entities.RoleEntity;

public interface UserDetail {
    Long getId();

    PersonEntity getPersonId();

    String getMailAddress();

    String getPhoneNumber();

    RoleEntity getRoleId();

    String getPassword();

}
