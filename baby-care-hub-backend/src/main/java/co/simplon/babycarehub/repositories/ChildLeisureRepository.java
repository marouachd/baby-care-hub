package co.simplon.babycarehub.repositories;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.babycarehub.entities.ChildLeisureEntity;

public interface ChildLeisureRepository
	extends JpaRepository<ChildLeisureEntity, Long> {

    List<ChildLeisureEntity> findByDateAndChildId(Date date,
	    Long childId);

}
