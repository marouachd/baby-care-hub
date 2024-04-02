package co.simplon.babycarehub.babybottels;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import co.simplon.babycarehub.actualities.ActualityEntity;
import co.simplon.babycarehub.actualities.ActualityRepository;

@Service
@Transactional
public class BabyBottelServiceImpl
	implements BabyBottelService {
    private BabyBottelRepository baby_bottels;
    private ActualityRepository actualities;

    public BabyBottelServiceImpl(
	    BabyBottelRepository baby_bottels,
	    ActualityRepository actualities) {
	this.baby_bottels = baby_bottels;
	this.actualities = actualities;
    }

    @Override
    public void create(BabyBottelCreateDto inputs) {

	Long childId = inputs.getChildId();
	Date date = inputs.getDate();

	ActualityEntity actuality = actualities
		.findByChildIdAndDate(childId, date);

	BabyBottelEntity entity = new BabyBottelEntity();
	entity.setChildId(inputs.getChildId());
	entity.setTime(inputs.getTime());
	entity.setVolume(inputs.getVolume());
	entity.setDate(inputs.getDate());

	System.out.println(actuality + "actuality");

	if (actuality == null) {
	    actuality = new ActualityEntity();
	    actuality.setChildId(inputs.getChildId());
	    actuality.setDate(inputs.getDate());
	    List<BabyBottelEntity> babyBottles = new ArrayList<>();
	    babyBottles.add(entity);
	    actuality.setBabyBottels(babyBottles);
	    actualities.save(actuality);

	} else {
	    List<BabyBottelEntity> babyBottles = new ArrayList<>();
	    babyBottles.add(entity);
	    actuality.setBabyBottels(babyBottles);
	    actualities.save(actuality);
	}
	entity.setActuality(actuality);
	baby_bottels.save(entity);

    }

    @Override
    public List<BabyBottelEntity> getAllByDateAndChildId(
	    Date date, Long childId) {

	return baby_bottels.findByDateAndChildId(date,
		childId);
    }

}
