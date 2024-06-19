package co.simplon.babycarehub.repositories;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.babycarehub.entities.ActualityEntity;

public interface ActualityRepository
	extends JpaRepository<ActualityEntity, Long> {

    ActualityEntity findByChildIdAndDate(Long childId,
	    Date date);

}
