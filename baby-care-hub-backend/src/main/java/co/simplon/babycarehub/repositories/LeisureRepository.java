package co.simplon.babycarehub.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.babycarehub.dtos.LabelValue;
import co.simplon.babycarehub.entities.LeisureEntity;

public interface LeisureRepository
	extends JpaRepository<LeisureEntity, Long> {

    Collection<LabelValue> findAllProjectBy();

}
