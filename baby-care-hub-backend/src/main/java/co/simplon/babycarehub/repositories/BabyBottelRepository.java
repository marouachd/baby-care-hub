package co.simplon.babycarehub.repositories;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.babycarehub.entities.BabyBottelEntity;

public interface BabyBottelRepository
	extends JpaRepository<BabyBottelEntity, Long> {

    List<BabyBottelEntity> findByDateAndChildId(Date date,
	    Long childId);

}
