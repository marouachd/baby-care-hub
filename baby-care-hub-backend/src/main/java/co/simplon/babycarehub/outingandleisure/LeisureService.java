package co.simplon.babycarehub.outingandleisure;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

import co.simplon.babycarehub.dtos.LabelValue;

public interface LeisureService {

    Collection<LabelValue> getAll();

    void create(ChildLeisureCreateDto inputs);

    List<ChildLeisureEntity> getAllByDateAndChildId(
	    Date date, Long childId);

}
