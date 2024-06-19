package co.simplon.babycarehub.services;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.simplon.babycarehub.dtos.ChildActivityCreateDto;
import co.simplon.babycarehub.dtos.LabelValue;
import co.simplon.babycarehub.entities.ActivityEntity;
import co.simplon.babycarehub.entities.ActualityEntity;
import co.simplon.babycarehub.entities.ChildActivityEntity;
import co.simplon.babycarehub.repositories.ActivityRepository;
import co.simplon.babycarehub.repositories.ActualityRepository;
import co.simplon.babycarehub.repositories.ChildActivityRepository;

@Service
@Transactional
public class ChildActivityServiceImpl
	implements ChildActivityService {

    private ActivityRepository activities;
    private ChildActivityRepository childs_activities;
    private ActualityRepository actualities;

    public ChildActivityServiceImpl(
	    ActivityRepository activities,
	    ChildActivityRepository childs_activities,
	    ActualityRepository actualities) {
	this.activities = activities;
	this.childs_activities = childs_activities;
	this.actualities = actualities;
    }

    @Override
    public Collection<LabelValue> getAll() {
	return activities.findAllProjectBy();
    }

    @Override
    public void create(ChildActivityCreateDto inputs) {
	Long childId = inputs.getChildId();
	Date date = inputs.getDate();

	ActualityEntity actuality = actualities
		.findByChildIdAndDate(childId, date);

	Long activityId = inputs.getActivityId();
	ActivityEntity activity = activities
		.getReferenceById(activityId);

	ChildActivityEntity childActivity = new ChildActivityEntity();
	childActivity.setActivityId(activity);

	childActivity.setChildId(inputs.getChildId());
	childActivity
		.setCommentaire(inputs.getCommentaire());

	LocalTime currentTime = LocalTime.now();
	Time time = Time.valueOf(currentTime);
	childActivity.setTime(time);
	childActivity.setDate(inputs.getDate());
	childs_activities.save(childActivity);
	if (actuality == null) {
	    actuality = new ActualityEntity();
	    actuality.setChildId(inputs.getChildId());
	    actuality.setDate(inputs.getDate());
	    actuality.setChildActivity(childActivity);
	    actualities.save(actuality);
	}

	else {
	    actuality.setChildActivity(childActivity);
	    actualities.save(actuality);
	}
    }

    @Override
    public List<ChildActivityEntity> getAllByDateAndChildId(
	    java.sql.Date date, Long childId) {
	return childs_activities.findByDateAndChildId(date,
		childId);
    }

}
