package co.simplon.babycarehub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.babycarehub.entities.UserEntity;

public interface AuthRepository
	extends JpaRepository<UserEntity, Long> {

    UserEntity getByMailAddress(String mailAddress);

}
