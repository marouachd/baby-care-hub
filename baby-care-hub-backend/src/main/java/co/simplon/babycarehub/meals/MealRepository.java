package co.simplon.babycarehub.meals;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MealRepository
	extends JpaRepository<MealEntity, Long> {

    List<MealEntity> findByDateAndChildIdAndType(Date date,
	    Long childId, String type);

    List<MealEntity> findByChildId(Long id);

}
