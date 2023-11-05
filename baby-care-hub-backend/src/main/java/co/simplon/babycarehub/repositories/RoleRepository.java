package co.simplon.babycarehub.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.babycarehub.dtos.LabelValue;
import co.simplon.babycarehub.entities.RoleEntity;

public interface RoleRepository
	extends JpaRepository<RoleEntity, Long> {

    Collection<LabelValue> findAllProjectBy();
}
