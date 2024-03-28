package co.simplon.babycarehub.babybottels;

import java.util.Date;

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
	baby_bottels.save(entity);

	System.out.println(actuality + "actuality");

	if (actuality == null) {
	    actuality = new ActualityEntity();
	    actuality.setChildId(inputs.getChildId());
	    actuality.setDate(inputs.getDate());
	    actuality.setBabybottel(entity);
	    actualities.save(actuality);

	} else {
	    actuality.setBabybottel(entity);
	    actualities.save(actuality);
	}

    }

}
