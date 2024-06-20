package co.simplon.babycarehub.services;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

import co.simplon.babycarehub.dtos.ChildLeisureCreateDto;
import co.simplon.babycarehub.dtos.LabelValue;
import co.simplon.babycarehub.entities.ChildLeisureEntity;

public interface LeisureService {

    Collection<LabelValue> getAll();

    void create(ChildLeisureCreateDto inputs);

    List<ChildLeisureEntity> getAllByDateAndChildId(
	    Date date, Long childId);

}
