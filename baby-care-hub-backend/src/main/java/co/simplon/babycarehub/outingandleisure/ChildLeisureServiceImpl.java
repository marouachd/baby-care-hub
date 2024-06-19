package co.simplon.babycarehub.outingandleisure;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.simplon.babycarehub.dtos.LabelValue;
import co.simplon.babycarehub.entities.ActualityEntity;
import co.simplon.babycarehub.repositories.ActualityRepository;

@Service
@Transactional
public class ChildLeisureServiceImpl
	implements LeisureService {

    private LeisureRepository leisures;
    private ChildLeisureRepository childs_leisures;
    private ActualityRepository actualities;

    public ChildLeisureServiceImpl(
	    LeisureRepository leisures,
	    ChildLeisureRepository childs_leisures,
	    ActualityRepository actualities) {

	this.leisures = leisures;
	this.childs_leisures = childs_leisures;
	this.actualities = actualities;
    }

    @Override
    public Collection<LabelValue> getAll() {
	return leisures.findAllProjectBy();
    }

    @Override
    public void create(ChildLeisureCreateDto inputs) {

	Long childId = inputs.getChildId();
	Date date = inputs.getDate();

	ActualityEntity actuality = actualities
		.findByChildIdAndDate(childId, date);

	ChildLeisureEntity childLeisure = new ChildLeisureEntity();

	childLeisure.setChildId(inputs.getChildId());
	childLeisure
		.setCommentaire(inputs.getCommentaire());

	Long leisureId = inputs.getLeisureId();
	LeisureEntity leisure = leisures
		.getReferenceById(leisureId);

	childLeisure.setLeisureId(leisure);
	childLeisure.setDate(inputs.getDate());
	childs_leisures.save(childLeisure);

	if (actuality == null) {
	    actuality = new ActualityEntity();
	    actuality.setChildId(inputs.getChildId());
	    actuality.setDate(inputs.getDate());
	    actuality.setLeisure(childLeisure);
	    actualities.save(actuality);
	} else {
	    actuality.setLeisure(childLeisure);
	    actualities.save(actuality);
	}

    }

    @Override
    public List<ChildLeisureEntity> getAllByDateAndChildId(
	    java.sql.Date date, Long childId) {
	return childs_leisures.findByDateAndChildId(date,
		childId);
    }

}
