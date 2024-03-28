package co.simplon.babycarehub.meals;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import co.simplon.babycarehub.actualities.ActualityEntity;
import co.simplon.babycarehub.actualities.ActualityRepository;

@Service
@Transactional
public class MealServiceImpl implements MealService {
    private MealRepository meals;
    private ActualityRepository actualities;

    public MealServiceImpl(MealRepository meals,
	    ActualityRepository actualities) {
	this.meals = meals;
	this.actualities = actualities;
    }

    @Override
    public void create(MealCreateDto inputs) {
	Long childId = inputs.getChildId();
	Date date = inputs.getDate();

	ActualityEntity actuality = actualities
		.findByChildIdAndDate(childId, date);

	MealEntity entity = new MealEntity();

	entity.setChildId(inputs.getChildId());

	entity.setCommentaire(inputs.getCommentaire());
	entity.setEval(inputs.getEval());
	entity.setType(inputs.getType());
	entity.setSnack(inputs.getSnack());
	meals.save(entity);

	if (actuality == null) {
	    actuality = new ActualityEntity();
	    actuality.setChildId(inputs.getChildId());
	    actuality.setDate(inputs.getDate());
	    actuality.setMeal(entity);
	    actualities.save(actuality);
	} else {
	    actuality.setMeal(entity);
	    actualities.save(actuality);
	}

    }
}
