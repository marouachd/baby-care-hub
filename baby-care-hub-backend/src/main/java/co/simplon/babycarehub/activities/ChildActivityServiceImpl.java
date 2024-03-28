package co.simplon.babycarehub.activities;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.simplon.babycarehub.actualities.ActualityEntity;
import co.simplon.babycarehub.actualities.ActualityRepository;
import co.simplon.babycarehub.dtos.LabelValue;

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
	ChildActivityEntity childActivity = new ChildActivityEntity();
	Long activityId = inputs.getActivityId();
	ActivityEntity activity = activities
		.getReferenceById(activityId);
	childActivity.setActivityId(activity);

	childActivity.setChildId(inputs.getChildId());
	childActivity
		.setCommentaire(inputs.getCommentaire());
	// childActivity.setTime(LocalTime.now().toString());
	// LocalTime currentTime = LocalTime.now();
	// String formattedTime = currentTime.format(
	// DateTimeFormatter.ofPattern("HH:mm:ss"));

	// childActivity.setTime(formattedTime);
	LocalTime currentTime = LocalTime.now();
	Time time = Time.valueOf(currentTime);
	childActivity.setTime(time);
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
}
