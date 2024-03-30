package co.simplon.babycarehub.meals;

import java.sql.Date;
import java.util.List;

public interface MealService {

    void create(MealCreateDto inputs);

    List<SnackEntity> getAll();

    List<MealEntity> getAllByDateAndChildIdAndType(
	    Date date, Long childId, String type);

}
