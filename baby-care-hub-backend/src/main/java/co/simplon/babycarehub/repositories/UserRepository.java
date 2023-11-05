package co.simplon.babycarehub.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.simplon.babycarehub.entities.UserEntity;

@Repository
public interface UserRepository
	extends JpaRepository<UserEntity, Long> {

    @Query("SELECT u FROM UserEntity u JOIN u.personId p WHERE p.pseudoName = :pseudoName")
    UserEntity findUserByPseudoName(
	    @Param("pseudoName") String pseudoName);

}
