package co.simplon.babycarehub.actualities;

import java.sql.Date;

import org.springframework.stereotype.Service;

@Service
public class ActualityServiceImpl
	implements ActualityService {

    private ActualityRepository actualities;

    public ActualityServiceImpl(
	    ActualityRepository actualities) {
	this.actualities = actualities;
    }

    @Override
    public ActualityEntity actualityDetail(Date date,
	    Long childId) {

	return actualities.findByChildIdAndDate(childId,
		date);
    }

}
