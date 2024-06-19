package co.simplon.babycarehub.meals;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import co.simplon.babycarehub.entities.ActualityEntity;
import co.simplon.babycarehub.repositories.ActualityRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class MealServiceImpl implements MealService {
    private MealRepository meals;
    private ActualityRepository actualities;
    private SnackRepository snacks;

    public MealServiceImpl(MealRepository meals,
	    ActualityRepository actualities,
	    SnackRepository snacks) {
	this.meals = meals;
	this.actualities = actualities;
	this.snacks = snacks;
    }

    @Override
    public void create(MealCreateDto inputs) {
	Long childId = inputs.getChildId();
	Date date = inputs.getDate();

	Long snackId = inputs.getSnackId();

	ActualityEntity actuality = actualities
		.findByChildIdAndDate(childId, date);

	MealEntity entity = new MealEntity();

	entity.setChildId(inputs.getChildId());

	entity.setCommentaire(inputs.getCommentaire());
	entity.setEval(inputs.getEval());
	entity.setType(inputs.getType());
	entity.setDate(inputs.getDate());

	if (actuality == null) {
	    actuality = new ActualityEntity();
	    actuality.setChildId(inputs.getChildId());
	    actuality.setDate(inputs.getDate());

	}
	if ("dejeuner".equals(inputs.getType())) {
	    actuality.setMeal(entity);
	} else {
	    actuality.setSnack(entity);
	    SnackEntity snack = snacks
		    .getReferenceById(snackId);
	    entity.setSnackId(snack);
	}

	meals.save(entity);
	actualities.save(actuality);

    }

    @Override
    public List<SnackEntity> getAll() {

	return snacks.findAll();
    }

    @Override
    public List<MealEntity> getAllByDateAndChildIdAndType(
	    Date date, Long childId, String type) {
	return meals.findByDateAndChildIdAndType(date,
		childId, type);
    }

}
