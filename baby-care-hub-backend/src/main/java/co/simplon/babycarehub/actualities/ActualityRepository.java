package co.simplon.babycarehub.actualities;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ActualityRepository
	extends JpaRepository<ActualityEntity, Long> {

    ActualityEntity findByChildIdAndDate(Long childId,
	    Date date);

}