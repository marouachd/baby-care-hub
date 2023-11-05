package co.simplon.babycarehub.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.babycarehub.dtos.LabelValue;
import co.simplon.babycarehub.entities.GenderEntity;

public interface GenderRepository
	extends JpaRepository<GenderEntity, Long> {

    Collection<LabelValue> findAllProjectBy();

}
