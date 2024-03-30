package co.simplon.babycarehub.meals;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.babycarehub.dtos.LabelValue;

public interface SnackRepository
	extends JpaRepository<SnackEntity, Long> {

    Collection<LabelValue> findAllProjectBy();

}
