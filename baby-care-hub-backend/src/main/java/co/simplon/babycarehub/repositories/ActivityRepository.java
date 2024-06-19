package co.simplon.babycarehub.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.babycarehub.dtos.LabelValue;
import co.simplon.babycarehub.entities.ActivityEntity;

public interface ActivityRepository
	extends JpaRepository<ActivityEntity, Long> {

    Collection<LabelValue> findAllProjectBy();

    ActivityEntity getReferenceById(Long id);

}
