package co.simplon.babycarehub.services;

import java.sql.Date;
import java.util.List;

import co.simplon.babycarehub.dtos.MealCreateDto;
import co.simplon.babycarehub.entities.MealEntity;
import co.simplon.babycarehub.entities.SnackEntity;

public interface MealService {

    void create(MealCreateDto inputs);

    List<SnackEntity> getAll();

    List<MealEntity> getAllByDateAndChildIdAndType(
	    Date date, Long childId, String type);

}
