package co.simplon.babycarehub.outingandleisure;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.babycarehub.dtos.LabelValue;

public interface LeisureRepository
	extends JpaRepository<LeisureEntity, Long> {

    Collection<LabelValue> findAllProjectBy();

}
