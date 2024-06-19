package co.simplon.babycarehub.services;

import java.sql.Date;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import co.simplon.babycarehub.entities.ActualityEntity;
import co.simplon.babycarehub.repositories.ActualityRepository;
import jakarta.transaction.Transactional;

@Service
public class ActualityServiceImpl
	implements ActualityService {

    private ActualityRepository actualities;

    public ActualityServiceImpl(
	    ActualityRepository actualities) {
	this.actualities = actualities;
    }

    @Override
    @Transactional
    public ActualityEntity actualityDetail(Date date,
	    Long childId) {

	ActualityEntity entity = actualities
		.findByChildIdAndDate(childId, date);
	if (entity != null) {
	    Hibernate.initialize(entity.getBabyBottels());
	    Hibernate.initialize(entity.getNap());
	    Hibernate.initialize(entity.getChildActivity());
	    Hibernate.initialize(entity.getLeisure());
	    Hibernate.initialize(entity.getMeal());
	    Hibernate.initialize(entity.getNap());
	    Hibernate.initialize(entity.getPresence());
	    Hibernate.initialize(entity.getSnack());
	    Hibernate.initialize(entity.getDate());

	}
	return entity;
    }

}
