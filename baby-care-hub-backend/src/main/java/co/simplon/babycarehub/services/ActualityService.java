package co.simplon.babycarehub.services;

import java.sql.Date;

import co.simplon.babycarehub.entities.ActualityEntity;

public interface ActualityService {

    ActualityEntity actualityDetail(Date date,
	    Long childId);

}
