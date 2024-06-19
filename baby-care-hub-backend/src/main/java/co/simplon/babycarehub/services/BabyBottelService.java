package co.simplon.babycarehub.services;

import java.sql.Date;
import java.util.List;

import co.simplon.babycarehub.dtos.BabyBottelCreateDto;
import co.simplon.babycarehub.entities.BabyBottelEntity;

public interface BabyBottelService {

    void create(BabyBottelCreateDto inputs);

    List<BabyBottelEntity> getAllByDateAndChildId(Date date,
	    Long childId);

}
