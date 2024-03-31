package co.simplon.babycarehub.activities;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

import co.simplon.babycarehub.dtos.LabelValue;

public interface ChildActivityService {

    Collection<LabelValue> getAll();

    void create(ChildActivityCreateDto inputs);

    List<ChildActivityEntity> getAllByDateAndChildId(
	    Date date, Long childId);

}
