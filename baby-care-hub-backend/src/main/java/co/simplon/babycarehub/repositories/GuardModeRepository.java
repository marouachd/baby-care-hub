package co.simplon.babycarehub.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.babycarehub.dtos.LabelValue;
import co.simplon.babycarehub.entities.GuardModeEntity;

public interface GuardModeRepository
	extends JpaRepository<GuardModeEntity, Long> {

    Collection<LabelValue> findAllProjectBy();

}
