package co.simplon.babycarehub.dtos;

import java.time.LocalDate;

import co.simplon.babycarehub.entities.GenderEntity;
import co.simplon.babycarehub.entities.GuardModeEntity;
import co.simplon.babycarehub.entities.PersonEntity;
import co.simplon.babycarehub.entities.UserEntity;

public interface ChildDetail {

    PersonEntity getPersonId();

    LocalDate getBirthdayDate();

    GuardModeEntity getGuardId();

    GenderEntity getGenderId();

    // UserEntity getChildminderCode();

    UserEntity getParentId();

    boolean getActive();

}
