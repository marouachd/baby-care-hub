package co.simplon.babycarehub.naps;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

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
    public void create(NapCreateDto inputs, String type) {

	Long childId = inputs.getChildId();
	Date date = inputs.getDate();
	String startTime = inputs.getStartTime();
	ActualityEntity actuality = actualities
		.findByChildIdAndDate(childId, date);

	NapEntity entity = naps.findByChildIdAndDateAndType(
		childId, date, type);

	if (entity == null || (entity != null
		&& entity.getEndTime() != null)
		&& entity.getType() == type) {

	    entity = new NapEntity();
	    entity.setType(type);
	    entity.setChildId(childId);
	    entity.setStartTime(startTime);
	    entity.setDate(date);
	    entity.setEndTime(inputs.getEndTime());
	    entity.setCommentaire(inputs.getCommentaire());

	} else {
	    entity.setEndTime(inputs.getEndTime());
	    entity.setCommentaire(inputs.getCommentaire());

	}

	if (actuality == null) {
	    actuality = new ActualityEntity();
	    actuality.setChildId(inputs.getChildId());
	    actuality.setDate(inputs.getDate());
	}
	if ("sieste".equals(type)) {
	    List<NapEntity> naps = new ArrayList<>();
	    naps.add(entity);
	    actuality.setNap(naps);

	} else if ("presence".equals(type)) {
	    actuality.setPresence(entity);
	}
	entity.setActuality(actuality);
	naps.save(entity);
	actualities.save(actuality);

    }

    @Override
    public List<NapEntity> getAllByDateAndChildIdAndType(
	    Date date, Long childId, String type) {

	return naps.findByDateAndChildIdAndType(date,
		childId, type);

    }

}
