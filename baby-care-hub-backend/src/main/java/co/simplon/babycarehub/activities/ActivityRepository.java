package co.simplon.babycarehub.activities;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.babycarehub.dtos.LabelValue;

public interface ActivityRepository
	extends JpaRepository<ActivityEntity, Long> {

    Collection<LabelValue> findAllProjectBy();

    ActivityEntity getReferenceById(Long id);

}
