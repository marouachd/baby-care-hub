package co.simplon.babycarehub.services;

import java.sql.Date;
import java.util.List;

import co.simplon.babycarehub.dtos.NapCreateDto;
import co.simplon.babycarehub.entities.NapEntity;

public interface NapService {

    void create(NapCreateDto inputs, String type);

    List<NapEntity> getAllByDateAndChildIdAndType(Date date,
	    Long childId, String type);

}
