package co.simplon.babycarehub.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.babycarehub.dtos.LabelValue;
import co.simplon.babycarehub.entities.SnackEntity;

public interface SnackRepository
	extends JpaRepository<SnackEntity, Long> {

    Collection<LabelValue> findAllProjectBy();

}
