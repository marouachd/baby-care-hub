package co.simplon.babycarehub.actualities;

import java.sql.Date;

public interface ActualityService {

    ActualityEntity actualityDetail(Date date,
	    Long childId);

}
