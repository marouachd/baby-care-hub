package co.simplon.babycarehub.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.simplon.babycarehub.dtos.UserDetail;
import co.simplon.babycarehub.entities.RoleEntity;
import co.simplon.babycarehub.entities.UserEntity;

@Repository
public interface UserRepository
	extends JpaRepository<UserEntity, Long> {

    @Query("SELECT u FROM UserEntity u JOIN u.personId p WHERE p.pseudoName = :pseudoName")
    UserEntity findUserByPseudoName(
	    @Param("pseudoName") String pseudoName);

    Optional<UserEntity> findById(Long parentId);

    UserDetail findProjectDetailById(Long id);

    UserEntity findUserById(Long userId);

    List<UserEntity> findAllByRoleId(RoleEntity role);

}
