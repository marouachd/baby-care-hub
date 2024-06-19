package co.simplon.babycarehub.services;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

import co.simplon.babycarehub.dtos.ChildActivityCreateDto;
import co.simplon.babycarehub.dtos.LabelValue;
import co.simplon.babycarehub.entities.ChildActivityEntity;

public interface ChildActivityService {

    Collection<LabelValue> getAll();

    void create(ChildActivityCreateDto inputs);

    List<ChildActivityEntity> getAllByDateAndChildId(
	    Date date, Long childId);

}
