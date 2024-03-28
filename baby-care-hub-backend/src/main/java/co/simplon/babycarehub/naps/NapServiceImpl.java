package co.simplon.babycarehub.naps;

import java.sql.Time;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import co.simplon.babycarehub.actualities.ActualityEntity;
import co.simplon.babycarehub.actualities.ActualityRepository;
import co.simplon.babycarehub.repositories.ChildRepository;

@Service
@Transactional
public class NapServiceImpl implements NapService {

    private NapRepository naps;
    private ActualityRepository actualities;

    public NapServiceImpl(NapRepository naps,
	    ChildRepository childs,
	    ActualityRepository actualities) {

	this.naps = naps;
	this.actualities = actualities;
    }

    @Override
    public void create(NapCreateDto inputs) {

	Long childId = inputs.getChildId();
	Date date = inputs.getDate();
	Time startTime = inputs.getStartTime();
	String type = inputs.getType();

	ActualityEntity actuality = actualities
		.findByChildIdAndDate(childId, date);

	NapEntity entity = naps
		.findByChildIdAndDate(childId, date);

	if (entity == null) {

	    entity = new NapEntity();
	    entity.setType(inputs.getType());
	    entity.setChildId(childId);
	    entity.setStartTime(startTime);
	    entity.setDate(date);
	    entity.setEndTime(inputs.getEndTime());
	    entity.setCommentaire(inputs.getCommentaire());
	    naps.save(entity);

	} else {
	    entity.setEndTime(inputs.getEndTime());
	    entity.setCommentaire(inputs.getCommentaire());
	    naps.save(entity);

	}

	if (actuality == null) {
	    actuality = new ActualityEntity();
	    actuality.setChildId(inputs.getChildId());
	    actuality.setDate(inputs.getDate());
	}
	actuality.setNap(entity);
	actualities.save(actuality);

    }

}
