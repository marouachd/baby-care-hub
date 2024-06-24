package co.simplon.babycarehub.repositories;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.babycarehub.entities.MealEntity;

public interface MealRepository
	extends JpaRepository<MealEntity, Long> {

    List<MealEntity> findByDateAndChildIdAndType(Date date,
	    Long childId, String type);

}
